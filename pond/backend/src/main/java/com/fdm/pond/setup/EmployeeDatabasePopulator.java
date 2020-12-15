package com.fdm.pond.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdm.pond.model.employee.Admin;
import com.fdm.pond.model.employee.Consultant;
import com.fdm.pond.model.employee.Manager;
import com.fdm.pond.model.employee.Skill;
import com.fdm.pond.model.opportunity.Opportunity;
import com.fdm.pond.model.schedule.Interview;
import com.fdm.pond.model.schedule.ScheduleEntry;
import com.fdm.pond.model.schedule.SelfLearning;
import com.fdm.pond.repository.EmployeeRepository;
import com.fdm.pond.repository.OpportunityRepository;
import com.fdm.pond.repository.ScheduleEntryRepository;
import com.fdm.pond.repository.SkillRepository;

@Component
public class EmployeeDatabasePopulator implements ApplicationRunner {

	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	SkillRepository skillRepo;
	@Autowired
	ScheduleEntryRepository scheduleEntryRepo;
	@Autowired
	OpportunityRepository opportunityRepo;
	private final PasswordEncoder passwordEncoder;
	@Autowired
	public EmployeeDatabasePopulator(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Admin admin = new Admin("admin@notfdm.com",passwordEncoder.encode("p"),"Admin","McAdmin");
		Consultant rhys = new Consultant("rhys@notfdm.com",passwordEncoder.encode("p"),"Rhys","McDonald");
		Consultant andy = new Consultant("andrew2@notfdm.com",passwordEncoder.encode("2"),"Andrew","Martin");
		
		Consultant tom = new Consultant("tom@notfdm.com",passwordEncoder.encode("p"),"Thomas","Suebwicha");
		Manager ed = new Manager("ed@notfdm.com",passwordEncoder.encode("e"),"Edward","Bristow");
		employeeRepo.save(admin);
		employeeRepo.save(tom);
		employeeRepo.save(rhys);
		employeeRepo.save(andy);
		employeeRepo.save(ed);
		addEmployeesWithSkills();
		System.out.println("Finished populating Employees");
	}
	
	public void addEmployeesWithSkills() {
		Skill skill = skillRepo.save(new Skill("Java"));
        //I understand you want to use a builder but it doesn't
        //make any sense for a consultant to use a builder
        Consultant ted = new Consultant("ted@notfdm.com",passwordEncoder.encode("p"),"Ted","dy");
        ted.addSkill(skill);
        employeeRepo.save(ted);
        Consultant tod = new Consultant("tod@notfdm.com",passwordEncoder.encode("p"),"Tod","dler");

        ted.addSkill(skill);
        tod.addSkill(skill);
        tod.removeSkill(skill);
        employeeRepo.save(ted);
        employeeRepo.save(tod);

        Opportunity opportunity = Opportunity.builder()
                .position("CEO")
                .build();
        opportunityRepo.save(opportunity);

        opportunity.addSkill(skill);
        opportunityRepo.save(opportunity);

        ScheduleEntry interview = Interview.builder()
                .time("now")
                .opportunity(opportunity)
                .build();
        interview.setContent("Interview for CEO");
        interview.setConsultant(ted);
        scheduleEntryRepo.save(interview);

        ScheduleEntry selfLearning = SelfLearning.builder()
                .description("React")
                .skill(skill)
                .build();
        selfLearning.setContent("self learning java");
        selfLearning.setConsultant(ted);
        scheduleEntryRepo.save(selfLearning);
	}

	
}
