package com.unitau.tgvinicius.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.entities.User;
import com.unitau.tgvinicius.enums.IssueState;
import com.unitau.tgvinicius.repositories.BranchRepository;
import com.unitau.tgvinicius.repositories.CommitRepository;
import com.unitau.tgvinicius.repositories.IssueRepository;
import com.unitau.tgvinicius.repositories.RepositoryRepository;
import com.unitau.tgvinicius.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RepositoryRepository repositoryRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private CommitRepository commitRepository;
	@Autowired
	private IssueRepository issueRepository;

	@Override
	public void run(String... args) throws Exception {
		
		repositoryRepository.deleteAll();
	    userRepository.deleteAll();
		branchRepository.deleteAll();
		commitRepository.deleteAll();
		issueRepository.deleteAll();
		
		 // Criando um repositório
	    Repository repo = new Repository("repo123", "MeuRepo", "Descrição", 10, 5, 2, LocalDate.now(), LocalDate.now());
	    repositoryRepository.save(repo);

	    // Criando usuários
	    User u1 = new User("user1", "vlsan_", "Vinícius Leonardo dos Santos");
	    User u2 = new User("user2", "zerinho", "Juscicleyson da Silva Nunes");
	    userRepository.saveAll(Arrays.asList(u1, u2));
	    
	 // Associando os usuários ao repositório
	    u1.getRepository().add(repo);
	    u2.getRepository().add(repo);

	    // Salvando os usuários com a associação
	    userRepository.saveAll(Arrays.asList(u1, u2));

	    // Criando commits com usuário e repositório vinculados
	    Commit c1 = new Commit("commit1", u1.getLogin(), LocalDate.now(), u1, repo);
	    Commit c2 = new Commit("commit2", u2.getLogin(), LocalDate.now(), u2, repo);
	    commitRepository.saveAll(Arrays.asList(c1, c2));

	    // Criando branches e vinculando ao último commit
	    Branch b1 = new Branch("branch1", "main", repo, c1);
	    Branch b2 = new Branch("branch2", "develop", repo, c2);
	    branchRepository.saveAll(Arrays.asList(b1, b2));
	    
	 // Criando issues vinculadas a repositório e usuário
	    Issue i1 = new Issue("issue1", "Bug na API", IssueState.OPEN, LocalDate.now(), repo, u1);
	    Issue i2 = new Issue("issue2", "Feature request: Dark mode", IssueState.CLOSED, LocalDate.now(), repo, u2);
	    issueRepository.saveAll(Arrays.asList(i1, i2));
	}
}