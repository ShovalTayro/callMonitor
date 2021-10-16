package com.example.demo.PhoneRecordController;

import com.example.demo.PhoneRecordService.PhoneRecordService;
import com.example.demo.PhoneRecord.PhoneRecord;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping(path = "/callmonitor")
public class phoneRecordController {

    private final PhoneRecordService phoneRecordService;
    
    @Autowired
    public phoneRecordController(PhoneRecordService phoneRecordService){
        this.phoneRecordService = phoneRecordService;
    }

    // add new Blog to Database
    @PostMapping
    public void receivingData(@RequestBody PhoneRecord phoneRecord){
        phoneRecordService.receivingData(phoneRecord);
    }

	// Get all call records of that number
    @GetMapping(value = "/callrecords/number/{number}")
    public List<PhoneRecord> getCallRecordsByNumber(@PathVariable String number){
        return phoneRecordService.getCallRecordsByNumber(number);
    }
    
    // Get all records that are bigger than some duration 
    @GetMapping(value = "/callrecords/duration/{duration}" )
    public List<PhoneRecord> getBiggerThanDuration(@PathVariable Integer duration){
        return phoneRecordService.getBiggerThanDuration(duration);
    }

    // Update new Number
    @PutMapping(path = "{phoneNumber}")
    public void UpdatePhoneNumber(@PathVariable("phoneNumber") String phoneNumber, @RequestParam(required = false) String newNumber) {
        phoneRecordService.UpdatePhoneNumber(phoneNumber ,newNumber);
    }
}
