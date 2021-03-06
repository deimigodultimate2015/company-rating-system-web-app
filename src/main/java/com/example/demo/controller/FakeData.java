package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.entities.Users;
import com.example.demo.model.repository.DepartRepository;
import com.example.demo.model.repository.RecordRepository;
import com.example.demo.model.repository.StaffRepository;
import com.example.demo.model.repository.UserRepository;

//
@Configuration
public class FakeData {

//	public Long randomNumber(int min, int max) {
//		return (long) ((Math.random() * ((max - min) + 1)) +min);
//	}
//	
//	public boolean randomBoolean () {
//		if(randomNumber(1,2) == 1) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	public Date randomDate() {
//		Random random = new Random();
//		int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
//		int maxDay = (int) LocalDate.of(2001, 1, 1).toEpochDay();
//		long randomDay = minDay + random.nextInt(maxDay - minDay);
//
//		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
//		return Date.from(randomBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//	}
	@Autowired
	UserRepository userRepository;
//	
	@Bean
	CommandLineRunner initFakeData(StaffRepository srepository, DepartRepository drepository, RecordRepository rrepository) {
		return args -> {
			Users user = new Users();
			user.setFullname("hana zuki");
			user.setUsername("hanazuki2010");
			user.setPassword("$2a$10$nu1M79bQKcLVkV2RsddRpu6nggerld.79ZM4/90DsE7Lv8cXVFueu");
			user.setActive(true);
			user.setEmail("thotx1999@gmail.com");
			userRepository.save(user);
		};
//		List<Staffs> slist = new ArrayList<>();
//		List<Departs> dlist = new ArrayList<>();
//		return args -> {
//			for (int i = 0; i < 52; i++) {
//				Departs depart = new Departs("Depart of the no."+i);
//				dlist.add(depart);
//				drepository.save(depart);
//			}
//			
//			for(int i = 0; i < 1000; i++) {
//				Staffs staff = new Staffs(
//						"Lee Huey Staff "+i,
//						randomBoolean(),
//						randomDate(),
//						"email"+i+"@gmail.com",
//						"03"+randomNumber(0, 9)+randomNumber(0, 9)+randomNumber(0, 9)+randomNumber(0, 9)+randomNumber(0, 9)+randomNumber(0, 9)+randomNumber(0, 9)+randomNumber(0, 9)+randomNumber(0, 9)+randomNumber(0, 9),
//						randomNumber(4000000, 20000000),
//						null,
//						Byte.parseByte(randomNumber(1,10)+""),
//						true,
//						dlist.get(Integer.parseInt(randomNumber(0, 51)+"")),
//						null
//						);
//				slist.add(staff);
//				srepository.save(staff);
//			}
//			
//			slist.forEach(staffx -> {
//				for(int i = 0; i < Integer.parseInt(randomNumber(10, 50)+""); i++) {
//					Records record = new Records(randomBoolean(),"This command no."+i+" is for "+staffx.getName(), new Date());
//					record.setStaff(staffx);
//					rrepository.save(record);
//				}
//			});
//			
//		};
	}
	
}
