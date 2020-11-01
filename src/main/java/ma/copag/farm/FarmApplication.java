package ma.copag.farm;


import ma.copag.farm.entities.*;
import ma.copag.farm.service.IFermeInitService;
import ma.copag.farm.services.AccountService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FarmApplication implements CommandLineRunner {

	@Autowired
	private IFermeInitService iFermeInitService;

	@Autowired
	public AccountService accountService;

	/*@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;*/

	public static void main(String[] args) {
		SpringApplication.run(FarmApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {//hachage de password
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {

		accountService.saveUser(new AppUser(null, "El azouzi", "Khalil", RandomString.make(15),
				"0603040506","admin@copag.ma","1234",null));
		accountService.saveUser(new AppUser(null, "Rafiq", "Abdo",RandomString.make(15),
				"0734568912", "agent.mdm@copag.ma","1234",null));
		accountService.saveUser(new AppUser(null, "Nourri", "Reda",RandomString.make(15),
				"0634653214","technicien@copag.ma","1234",null));
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "TECHNICIEN"));
		accountService.saveRole(new AppRole(null, "MANAGER"));
		accountService.addRoleToUser("admin@copag.ma", "ADMIN");
		accountService.addRoleToUser("agent.mdm@copag.ma", "MANAGER");
		accountService.addRoleToUser("technicien@copag.ma", "TECHNICIEN");


		//repositoryRestConfiguration.exposeIdsFor(Ferme.class,Secteur.class,Parcelle.class);
		iFermeInitService.initVille();
		iFermeInitService.initFerme();
		iFermeInitService.initSecteur();
		iFermeInitService.initParcelle();
		iFermeInitService.initPompeStation();
		iFermeInitService.initApporEau();
		iFermeInitService.initGroupVariete();
		iFermeInitService.initPortGreffe();
		iFermeInitService.initVariete();
		iFermeInitService.initTestConf();

/*		Secteur secteur = secteurRepository.findById((long)1).get();
		Parcelle parcelle = parcelleRepository.findBySecteurAndAndId(secteur,(long) 1);
		parcelle.toString();*/

	}
}
