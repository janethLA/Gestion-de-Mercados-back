package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.sisgestionMercados.Input.AuthUserFinal;
import com.dev.sisgestionMercados.Input.FinalUserInput;
import com.dev.sisgestionMercados.Output.FinalUserAtributesOutput;
import com.dev.sisgestionMercados.Output.FinalUserDataOutput;
import com.dev.sisgestionMercados.Output.FinalUserOutput;
import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.OrderDetail;
import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.FinalUserRepository;

@Service
public class FinalUserService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private FinalUserRepository finalUserRepository ;
	@Autowired
	private PrivilegeService privilegeService ;
	@Autowired
	private UserService userService ;
	

	@Transactional
	public FinalUserInput save(FinalUser finalUser) {
		
		FinalUser persistedFinalUser;
		FinalUserInput saved=new FinalUserInput();
		String code=UUID.randomUUID().toString().toUpperCase().substring(0, 6);
		try {
			Privilege privilege=privilegeService.findByPrivilege("ROLE_FINAL_USER");
			System.out.println("-----------:"+privilege.getPrivilege()+" y "+privilege.getIdPrivilege());
			finalUser.setActive(false);
			finalUser.setCode(code);
			finalUser.setWhatsappLink("https://wa.me/591"+finalUser.getTelephone());
		    persistedFinalUser = finalUserRepository.save(finalUser);
			persistedFinalUser.setPrivilege(privilege);
		} catch (Exception e) {
			Privilege privilege=new Privilege();
			privilege.setPrivilege("ROLE_FINAL_USER");
			Privilege newPrivilege=privilegeService.save(privilege);
			finalUser.setActive(false);
			finalUser.setCode(code);
			finalUser.setWhatsappLink("https://wa.me/591"+finalUser.getTelephone());
		    persistedFinalUser = finalUserRepository.save(finalUser);
			persistedFinalUser.setPrivilege(newPrivilege);
		}
		saved.setIdFinalUser(persistedFinalUser.getIdFinalUser());
		saved.setCode(code);
		/*Para enviar mensaje de texto
		String phone=  Integer.toString(persistedFinalUser.getTelephone());
		Sms message=new Sms();
		message.sendSms(code, phone);
		
		Para enviar email
		String message="Su código de verificación es: "+code;
		String subject="Verifica tu Email!";
		emailService.sendEmail(finalUser.getEmail(), subject, message);*/
		
	    return saved;
	}
	
	public FinalUser findById(long idFinalUser) {
		return finalUserRepository.findById(idFinalUser).get();
	}
	
	public String verify(FinalUserInput user) {
		String result;
		FinalUser userF=findById(user.getIdFinalUser());
		if(userF.getCode().equals(user.getCode())) {
			userF.setActive(true);
			finalUserRepository.save(userF);
			result = "verificado";
		}else {
			result="no ha sido verificado";
		}
		return result;
	}
	
	public FinalUserOutput authUser(AuthUserFinal authUserFinal)  {
		
		FinalUserOutput finalUserOutput=null;
		List<FinalUser> allUser=finalUserRepository.findAll();
		
		for(FinalUser found: allUser) {
			
			if(authUserFinal.getUserName().equals(found.getUserName()) && authUserFinal.getTelephone()==found.getTelephone()) {
				finalUserOutput=new FinalUserOutput();
				finalUserOutput.setIdFinalUser(found.getIdFinalUser());
				finalUserOutput.setFinalUserName(found.getFinalUserName());
				finalUserOutput.setUserName(found.getUserName());
			}
		}
		
		return finalUserOutput;
	}
	
	public FinalUserDataOutput getFinalUser(int id) {
		
		FinalUser finalUser=findById(id);
		FinalUserDataOutput user=new FinalUserDataOutput();
		user.setIdFinalUser(finalUser.getIdFinalUser());
		user.setUserName(finalUser.getUserName());
		user.setPassword(finalUser.getPassword());
		user.setFinalUserName(finalUser.getFinalUserName());
		user.setTelephone(finalUser.getTelephone());
		return user;
	}
	
	public FinalUserAtributesOutput updateDataUser(AuthUserFinal finalUser, long id) {
		
		FinalUser newFinalUser=findById(id);
		String code="";
		FinalUserAtributesOutput finalUserInput=new FinalUserAtributesOutput();
		
		if(!finalUser.getUserName().isEmpty()) {
			newFinalUser.setUserName(finalUser.getUserName());
		}
		if(finalUser.getTelephone()!=0) {
			code=UUID.randomUUID().toString().toUpperCase().substring(0, 6);
			newFinalUser.setCode(code);
		}
		if(!finalUser.getPassword().isEmpty()) {
			
			newFinalUser.setPassword(finalUser.getPassword()); 
		}
		finalUserRepository.save(newFinalUser);
		finalUserInput.setIdFinalUser(newFinalUser.getIdFinalUser());
		finalUserInput.setTelephone(finalUser.getTelephone());
		finalUserInput.setCode(code);
		finalUserInput.setPassword(newFinalUser.getPassword());
		
		return finalUserInput;
	}
	
	public String verifyTelephone(FinalUserAtributesOutput user) {
		String result;
		FinalUser userF=findById(user.getIdFinalUser());
		if(userF.getCode().equals(user.getCode())) {
			userF.setTelephone(user.getTelephone());
			finalUserRepository.save(userF);
			result = "verificado, su número ha sido actualizado";
		}else {
			result="no ha sido verificado";
		}
		return result;
	}
	
	/*public String verifyEmail(FinalUserAtributesOutput user) {
		String result;
		FinalUser userF=findById(user.getIdFinalUser());
		if(userF.getCode().equals(user.getCode())) {
			userF.setEmail(user.getEmail());
			finalUserRepository.save(userF);
			result = "verificado, su email ha sido actualizado";
		}else {
			result="no ha sido verificado";
		}
		return result;
	}*/
	
    public boolean noExistsEmailAll(String email) {
		    	
    	boolean result=true;
    	List <UserS> allUser = userService.findAll();
		/*List <FinalUser> allUsers = finalUserRepository.findAll();
		for(FinalUser a: allUsers) {
			if(a.getEmail()!=null){
			if(a.getEmail().equalsIgnoreCase(email)) {
				result=false;
			}}
		}*/
		for(UserS a:allUser) {
			if(a.getEmail()!=null){
			if(a.getEmail().equalsIgnoreCase(email)) {
				result=false;
			}}
		}
		return result;
	}
    
    public boolean noExistsTelephone(int telephone) {
    	
    	boolean result=true;
    	List <UserS> allUser = userService.findAll();
		List <FinalUser> allUsers = finalUserRepository.findAll();
		for(FinalUser a: allUsers) {
			if(a.getTelephone()!=0){
			if(a.getTelephone()==telephone) {
				result=false;
			}}
		}
		for(UserS a:allUser) {
			if(a.getTelephone()!=0){
			if(a.getTelephone()==telephone) {
				result=false;
			}}
		}
		return result;
	}
    
    public boolean noExistsUserName(String userName) {
    	
    	boolean result=true;
    	List <UserS> allUser = userService.findAll();
		List <FinalUser> allUsers = finalUserRepository.findAll();
		for(FinalUser a: allUsers) {
			if(a.getUserName()!=null){
			if(a.getUserName().equalsIgnoreCase(userName)) {
				result=false;
			}}
		}
		for(UserS a:allUser) {
			if(a.getUserName()!=null){
			if(a.getUserName().equalsIgnoreCase(userName)) {
				result=false;
			}}
		}
		return result;
	}
    
    public List<FinalUser> findAll(){
    	return finalUserRepository.findAll();
    }
    
    /*public boolean noExistsEmail(String email) {
    	
    	boolean result=true;
		List <FinalUser> allUsers = finalUserRepository.findAll();
		for(FinalUser a: allUsers) {
			if(a.getEmail()!=null){
			if(a.getEmail().equalsIgnoreCase(email)) {
				result=false;
			}}
		}
		
		return result;
	}*/
    
}
