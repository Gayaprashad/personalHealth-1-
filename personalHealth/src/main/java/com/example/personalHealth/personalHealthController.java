package com.example.personalHealth;

import  com.example.personalHealth.bp;
import  com.example.personalHealth.bpRepository;
import  com.example.personalHealth.sugar;
import  com.example.personalHealth.sugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class personalHealthController {
    @Autowired
    private bpRepository repository;

    @Autowired
    private sugarRepository sRepository;

    public personalHealthController(bpRepository repository){this.repository= repository;}

    @GetMapping("/get-all-bp")
    public List<bpUI> getAllBp(){
        System.out.println("hey this is the getAllBP function");
        System.out.println(repository.findAll());
        List<bp> bpRecords = repository.findAll();
        List<bpUI> bpUI = new ArrayList<>();
        for (bp bpRecord: bpRecords){
            System.out.println(bpRecord.getRecordDate()+" "+bpRecord.getMorning()+" "+bpRecord.getAfternoon()+" "+bpRecord.getNight());
            bpUI.add(new bpUI(bpRecord.getId(),bpRecord.getRecordDate(),bpRecord.getMorning(), bpRecord.getAfternoon(),bpRecord.getNight()));
        }
        return bpUI;
    }

    @GetMapping("/get-one-bp")
    public Optional<bp> getOneBp(@RequestParam Long id){
        System.out.println("hey this is the getOneBP function");
        System.out.println(repository.findAll());
        return repository.findById(id);
    }

    @GetMapping("/get-one-sugar")
    public Optional<sugar> getOneSugar(@RequestParam Long id){
        System.out.println("hey this is the getOneSUgar function");
        System.out.println(sRepository.findAll());
        return sRepository.findById(id);
    }

    @GetMapping("/get-all-sugar")
    public List<sugarUI> getAllSugar(){
        System.out.println("hey this is the getAllSugar function");
        System.out.println(sRepository.findAll());
        List<sugar> sugarRecords = sRepository.findAll();
        List<sugarUI> sugarUI = new ArrayList<>();
        for (sugar sugarRecord: sugarRecords){
            System.out.println(sugarRecord.getRecordDate()+" "+sugarRecord.getMorning()+" "+sugarRecord.getAfternoon()+" "+sugarRecord.getNight());
            sugarUI.add(new sugarUI(sugarRecord.getId(),sugarRecord.getRecordDate(),sugarRecord.getMorning(), sugarRecord.getAfternoon(),sugarRecord.getNight()));
        }
        return sugarUI;
    }

    @PostMapping("/set-sugar-all")
//    ResponseEntity<String>
    public void createSugar(@RequestBody sugarUI sugarRecord) throws ParseException {
        List <sugar>sugarDatas = sRepository.findAll();

        float flag=0;
        for (sugar data:sugarDatas){
            if (data.getRecordDate().compareTo(sugarRecord.getRecordDate())==0) {

                flag = 1;
            }
            System.out.println(data.getRecordDate()+" "+sugarRecord.getRecordDate());
        }

        if(flag==0){
            sRepository.save(new sugar(sugarRecord.getRecordDate(),sugarRecord.getMorning(), sugarRecord.getAfternoon(), sugarRecord.getNight()));
        }else {
            // A sugar record with the same date already exists
        }
    }

    @PostMapping("/set-bp-all")
    public void createBp(@RequestBody bpUI bpRecord) throws ParseException {
        List <bp>bpDatas = repository.findAll();

        float flag=0;
        for (bp data:bpDatas){
            if (data.getRecordDate().compareTo(bpRecord.getRecordDate())==0) {
                flag = 1;
            }
        }

        if(flag==0){
            repository.save(new bp(bpRecord.getRecordDate(),bpRecord.getMorning(), bpRecord.getAfternoon(), bpRecord.getNight()));
        }else {
//            return new ResponseEntity<>("A BP record already exists with the given date", HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping("/set-bp-one")
//    public String createBp(@RequestBody bpValueUI bpRecord) throws ParseException {
//        List<bp> bpDatas= repository.findAll();
//
//        for( bp data :bpDatas){
//            if (data.getRecordDate().compareTo(bpRecord.getrecordDate())==0){
//                if (bpRecord.getMan().equals("morning")){
//                    Date tRecordDate= data.getRecordDate();
//                    String tNight = data.getNight();
//                    String tAfternoon = data.getAfternoon();
//                    repository.deleteById(data.getId());
//                    repository.save(new bp(tRecordDate,bpRecord.getValue(),tAfternoon,tNight));
//
//                }else if (bpRecord.getMan().equals("afternoon")){
//                    Date tRecordDate= data.getRecordDate();
//                    String tNight = data.getNight();
//                    String tMorning = data.getMorning();
//                    repository.deleteById(data.getId());
//
//                    repository.save(new bp(tRecordDate,tMorning,bpRecord.getValue() ,tNight));
//                } else if (bpRecord.getMan().equals("night")){
//                    Date tRecordDate= data.getRecordDate();
//                    String tMorning = data.getMorning();
//                    String tAfternoon = data.getAfternoon();
//                    repository.deleteById(data.getId());
//                    repository.save(new bp(tRecordDate,tMorning,tAfternoon,bpRecord.getValue()));
//                }else{
//                    System.out.println("Invalid MAN value in the HTTP Request");
//                    System.out.println(bpRecord.getValue());
//                    System.out.println(bpRecord.getMan());
//                }
//                System.out.print("date is being invoked");
//            }
//            System.out.println(data.getRecordDate()+" "+bpRecord.getrecordDate());
//
//
//        }
//        return "BP Record is modified";
//    }

    @PostMapping("/set-bp-one")
    public void createBpAngular(@RequestBody bp bpRecord) throws ParseException {
        Iterable<Long> id= Collections.singleton(bpRecord.getId());
        List<bp> bpDatas=repository.findAllById(id);
        for( bp data :bpDatas){
                Long idL= data.getId();
                Date recordDate=data.getRecordDate();
                String morning =data.getMorning();
                String afternoon =data.getAfternoon();
                String night =data.getNight();
                repository.deleteById(idL);
                repository.save(new bp(idL,bpRecord.getRecordDate(),bpRecord.getMorning(),bpRecord.getAfternoon(),bpRecord.getNight()));
        }
    }

//    @PutMapping("/set-sugar-one")
//    public String createSugar(@RequestBody sugarValueUI sugarRecord) throws ParseException {
//        List<sugar> sugarDatas= sRepository.findAll();
//
//        for( sugar data :sugarDatas){
//            if (data.getRecordDate().compareTo(sugarRecord.getrecordDate())==0){
//                if (sugarRecord.getMan().equals("morning")){
//                    Date tRecordDate= data.getRecordDate();
//                    String tNight = data.getNight();
//                    String tAfternoon = data.getAfternoon();
//                    sRepository.deleteById(data.getId());
//                    sRepository.save(new sugar(tRecordDate,sugarRecord.getValue(),tAfternoon,tNight));
//
//                }else if (sugarRecord.getMan().equals("afternoon")){
//                    Date tRecordDate= data.getRecordDate();
//                    String tNight = data.getNight();
//                    String tMorning = data.getMorning();
//                    sRepository.deleteById(data.getId());
//
//                    sRepository.save(new sugar(tRecordDate,tMorning,sugarRecord.getValue() ,tNight));
//                } else if (sugarRecord.getMan().equals("night")){
//                    Date tRecordDate= data.getRecordDate();
//                    String tMorning = data.getMorning();
//                    String tAfternoon = data.getAfternoon();
//                    sRepository.deleteById(data.getId());
//                    sRepository.save(new sugar(tRecordDate,tMorning,tAfternoon,sugarRecord.getValue()));
//                }else{
//                    System.out.println("Invalid MAN value in the HTTP Request");
//                    System.out.println(sugarRecord.getValue());
//                    System.out.println(sugarRecord.getMan());
//                }
//                System.out.print("date is being invoked");
//            }
//            System.out.println(data.getRecordDate()+" "+sugarRecord.getrecordDate());
//        }
//        return "Sugar Record is modified";
//    }

    @PostMapping("/set-sugar-one")
    public void createSugarAngular(@RequestBody sugar sugarRecord) throws ParseException {
        Iterable<Long> id= Collections.singleton(sugarRecord.getId());
        List<sugar> sugarDatas=sRepository.findAllById(id);
        for( sugar data :sugarDatas){
            Long idL= data.getId();
            Date recordDate=data.getRecordDate();
            String morning =data.getMorning();
            String afternoon =data.getAfternoon();
            String night =data.getNight();
            sRepository.deleteById(idL);
            sRepository.save(new sugar(idL,sugarRecord.getRecordDate(),sugarRecord.getMorning(),sugarRecord.getAfternoon(),sugarRecord.getNight()));
        }
    }

    @DeleteMapping("/delete-bp")
    public void deleteByBpId(@RequestParam Long id){
        repository.deleteById(id);
    }

    @DeleteMapping("/delete-sugar")
    public void deleteBySugarId(@RequestParam Long id){
        sRepository.deleteById(id);
    }
}










//Test BP function using parameters rather than the body
//    @PostMapping("/set-bp")
//    public String createBp(@RequestParam String recordDate, @RequestParam String morning, @RequestParam String afternoon, @RequestParam String night) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date rDate= formatter.parse(recordDate);
//        System.out.println(rDate);
//        bpUI bpRecord = new bpUI(rDate,morning, afternoon, night);
//        repository.save(new bp(rDate, morning, afternoon, night));
//        return " "+bpRecord.getRecordDate()+" "+bpRecord.getMorning()+" "+bpRecord.getAfternoon()+" "+bpRecord.getNight();
//    }

