package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exception.PinCodeNotFoundException;
import com.example.demo.model.PinDetails;
import com.example.demo.repository.PinDetailsRepo;

@Service
public class PinService {
 @Autowired
 private PinDetailsRepo pinrepo;
 
 
 
// To add  Pindetails to provide service in that area...
 public PinDetails createDetails(PinDetails pin)
 {
	 return pinrepo.save(pin);
 }

 
//To getall pindetails to show all service area to the Admin.. 
	public List<PinDetails> getall()
	{
		return pinrepo.findAll();
	}
	
	
//To update pin details if some information is typed wrong.
	public PinDetails getupdate(int id,PinDetails pin)
	{
		if(pinrepo.findById(id).isPresent()) {
		System.out.println(pin);
		Optional<PinDetails> pi=pinrepo.findById(id);
		PinDetails pik=pi.get();
		pik.setDistrict(pin.getDistrict());
		pik.setState(pin.getState());
		pik.setTown(pin.getTown());
		pik.setCountry(pin.getCountry());
		return pinrepo.save(pik);
		}
		else {
			throw new PinCodeNotFoundException("Sorry Pincode Doesn't Exist");
		}
	}
	
	
// delete the pin code to restrict to provide service in that area. 	
	public String delete(int id)
	{
		if(pinrepo.findById(id).isPresent())
			{
					pinrepo.deleteById(id);
					return "Pincode Deleted :" +id;
				
			}
		else {
			 throw new PinCodeNotFoundException("Sorry Pincode Doesn't Exist");
		}
	}

//to check by user whether hotel is providing service or not.........
	public Optional<PinDetails> getid(int id)
	{      
		Optional<PinDetails> f=pinrepo.findById(id);
		if(f.isPresent())
		{
			return f;
		}
		else {
			 throw new PinCodeNotFoundException("Sorry, We are not giving any Service in this area of PinCode : "+(int)id);
		}
	}
}
