package com.example.demo.PhoneRecordService;

import com.example.demo.PhoneRecord.PhoneRecord;
import com.example.demo.PhoneRecordRepository.PhoneRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.transaction.Transactional;

@Service
public class PhoneRecordService {

	private final PhoneRecordRepository phoneRecordRepository;
	private Map<String, String> blackList = new HashMap<String,String>();
	private Map<String, String> contacts = new HashMap<String,String>();

	@Autowired
	public PhoneRecordService(PhoneRecordRepository phoneRecordRepository) throws IOException{
		this.phoneRecordRepository = phoneRecordRepository;
		String row = "";

		BufferedReader blackListReader = null;
		try{
			blackListReader = new BufferedReader(new FileReader("blackList.csv"));
			row = blackListReader.readLine(); // For the 'phone' word
			while ((row = blackListReader.readLine()) != null) {
				blackList.put(row, row);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		blackListReader.close();

		BufferedReader contactsReader = null;
		try{
			contactsReader = new BufferedReader(new FileReader("contactList.csv"));
			row = contactsReader.readLine(); // For the 'name' and 'phone' words
			while ((row = contactsReader.readLine()) != null) {
				String[] words = row.split(",");
				contacts.put(words[1],words[1]);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		contactsReader.close();
	}

    public void receivingData(PhoneRecord phoneRecord){
		String number = phoneRecord.getPhoneNumber();

		if(number == null){
			throw new IllegalStateException("You must give phone number");
		}
		if(blackList.get(number) != null) throw new IllegalStateException("This number: " + number + "is a blocked number");

		if((!phoneRecord.getCallType().equals("Incoming")) ||phoneRecord.getCallType().equals("Outgoing"))
			throw new IllegalStateException("callType can be only 'Incoming' or 'Outgoing' ");
		
		if(phoneRecord.getDuration() < 0) throw new IllegalStateException("The call duration can't be negative");
		
		if(contacts.get(number) != null) phoneRecord.setsavedContact(true);

		phoneRecordRepository.save(phoneRecord);
	}

	public List<PhoneRecord> getCallRecordsByNumber(String number){
		if(phoneRecordRepository.findAllByPhoneNumber(number).isEmpty()){
            throw new IllegalStateException("there is no records for the number: " + number);
        }
        return phoneRecordRepository.findAllByPhoneNumber(number);
	}

	public List<PhoneRecord> getBiggerThanDuration(int duration){
		return phoneRecordRepository.findAllByDurationGreaterThan(duration);
		
	}
	
	@Transactional
	public void UpdatePhoneNumber(String phoneNumber ,String newNumber) {
		List<PhoneRecord> phoneRecords = phoneRecordRepository.findAllByPhoneNumber(phoneNumber);
		if(phoneRecords.isEmpty()) throw new IllegalStateException("record with number " + phoneNumber + " does not exists" );
	
		if (newNumber != null && newNumber.length() > 0){
			for(int i =0; i < phoneRecords.size(); i++){
				phoneRecords.get(i).setPhoneNumber(newNumber);
			}
		}
		//Update the contacts list
		if(contacts.get(phoneNumber) != null){
			contacts.remove(phoneNumber);
			contacts.put(newNumber, newNumber);
		}
	}
	
    
}
