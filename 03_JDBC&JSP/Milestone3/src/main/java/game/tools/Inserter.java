package game.tools;

import game.dal.*;
import game.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UserDao userDao = UserDao.getInstance();
		User riddhiUser = new User(1, "riddhi.gohil", "Riddhi", "Gohil", "riddhi.gohil@a.com");
        User johnUser = new User(2, "john.doe", "John", "Doe", "john.doe@a.com");
        riddhiUser = userDao.create(riddhiUser);
        johnUser = userDao.create(johnUser);

		
        ApplicantDao applicantDao = ApplicantDao.getInstance();
        Applicant janeApplicant = new Applicant(3, "jane.smith", "Jane", "Smith", "jane.smith@a.com",Applicant.Program.MASTERS, "Jane's essay content");
        Applicant paulApplicant = new Applicant(4, "paul.walker", "Paul", "Walker", "paul.walker@a.com",Applicant.Program.PHD, "Paul's essay content");
//        Applicant akonApplicant = new Applicant(10, "akon.morris", "Akon", "Morris", "akon.morris@a.com",Applicant.Program.MASTERS, "Akon's essay content");
        
        janeApplicant = applicantDao.create(janeApplicant);
        paulApplicant = applicantDao.create(paulApplicant);
//        akonApplicant = applicantDao.create(akonApplicant);
        
        ReviewerDao reviewerDao = ReviewerDao.getInstance();
        Reviewer jonesReviewer = new Reviewer(5, "jeffrey.jones", "Jeffrey", "Jones", "jeffrey.jones@a.com", Reviewer.Program.MASTERS);
        Reviewer meeraReviewer = new Reviewer(6, "meera.malhotra", "Meera", "Malhotra", "meera.malhotra@a.com",Reviewer.Program.MASTERS);
        jonesReviewer = reviewerDao.create(jonesReviewer);
        meeraReviewer = reviewerDao.create(meeraReviewer);
        
		LetterWriterDao letterWriterDao = LetterWriterDao.getInstance();
        LetterWriter perryLetterWriter = new LetterWriter(7, "matthew.perry", "Matthew", "Perry", "mp@a.com", "Northeastern University");
        LetterWriter gellerLetterWriter = new LetterWriter(8, "monica.geller", "Monica", "Geller", "mg@a.com", "Purdue University");
        perryLetterWriter = letterWriterDao.create(perryLetterWriter);
        gellerLetterWriter = letterWriterDao.create(gellerLetterWriter);
		
		DegreeDao degreeDao = DegreeDao.getInstance();
        Degree janeDegree = new Degree(janeApplicant, Degree.DegreeType.MASTERS, "Northeastern University", "Computer Science", new Date());
        Degree paulDegree = new Degree(paulApplicant, Degree.DegreeType.PHD, "University of Alabama", "Physics", new Date());
        janeDegree = degreeDao.create(janeDegree);
        paulDegree = degreeDao.create(paulDegree);
		
		RecLetterDao recLetterDao = RecLetterDao.getInstance();
        RecLetter janeRecLetter = new RecLetter(perryLetterWriter, janeApplicant, new Date(), "Jane's Recommendation Letter");
        RecLetter paulRecLetter = new RecLetter(gellerLetterWriter, paulApplicant, new Date(), "Paul's Recommendation Letter");
        recLetterDao.create(janeRecLetter);
        recLetterDao.create(paulRecLetter);
		
		
		RatingDao ratingDao = RatingDao.getInstance();
		Rating janeRating1 = new Rating(jonesReviewer, janeApplicant, 5);
		Rating janeRating2 = new Rating(meeraReviewer, janeApplicant, 8);
		Rating paulRating1 = new Rating(jonesReviewer, paulApplicant, 6);
		Rating paulRating2 = new Rating(meeraReviewer, paulApplicant, 7);
		ratingDao.create(janeRating1);
		ratingDao.create(janeRating2);
		ratingDao.create(paulRating1);
		ratingDao.create(paulRating2);
		
		
		// 1. User
		System.out.println("\n\n=========================================\n\n");
		User u1 = userDao.getUserByUserID(1);
		System.out.format("Reading User for userID=1 -> \n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n",
			u1.getUserName(), u1.getFirstName(), u1.getLastName(),u1.getEmail());
		System.out.println("\n\n=========================================\n\n");
		
		
		System.out.println("\n\n=========================================\n\n");
		User u2 = userDao.getUserByUserID(2);
		System.out.format("Deleting User of userID=2 -> \n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n", 
				u2.getUserName(), u2.getFirstName(), u2.getLastName(),u2.getEmail());
		u2 = userDao.delete(u2);
		System.out.println("\n\n=========================================\n\n");
		
		
		
		// 2. Applicant
		System.out.println("\n\n=========================================\n\n");
		Applicant a1 = applicantDao.getApplicantByUserID(3);
		System.out.format("Reading Applicant of applicantID=3 -> \n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n\tProgram:%s \n\tEssay:%s ",
				a1.getUserName(), a1.getFirstName(), a1.getLastName(),a1.getEmail(), a1.getProgram().name(), a1.getEssay());
		System.out.println("\n\n=========================================\n\n");
		
		
		System.out.println("\n\n=========================================\n\n");
		List<Applicant> applicantsByProgram = applicantDao.getApplicantsByProgram(Applicant.Program.MASTERS);
		System.out.println("Masters Program Applicants ->");
		for(Applicant a : applicantsByProgram) {
			System.out.format("\n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n\tProgram:%s \n\tEssay:%s ",a.getUserName(), a.getFirstName(), a.getLastName(),a.getEmail(), a.getProgram(), a.getEssay());
		}
		System.out.println("\n\n=========================================\n\n");
		
		
		System.out.println("\n\n=========================================\n\n");
		Applicant a2 = applicantDao.getApplicantByUserID(4);
		System.out.format("Deleting User of applicantID=4 -> \n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n\tProgram:%s \n\tEssay:%s \n", 
				a2.getUserName(), a2.getFirstName(), a2.getLastName(),a2.getEmail(), a2.getProgram().name(),a2.getEssay());
		a2 = applicantDao.delete(a2);
		System.out.println("\n\n=========================================\n\n");
		
		
		// 3. Reviewer
		System.out.println("\n\n=========================================\n\n");
		Reviewer rw1 = reviewerDao.getReviewerByUserID(5);
		System.out.format("Reading Reviewer for userID=5 -> \n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n\tProgram:%s \n",
			rw1.getUserName(), rw1.getFirstName(), rw1.getLastName(),rw1.getEmail(), rw1.getProgram());
		System.out.println("\n\n=========================================\n\n");
		
		
		System.out.println("\n\n=========================================\n\n");
		Reviewer rw2 = reviewerDao.getReviewerByUserID(6);
		System.out.format("Reading Reviewer for userID=6 -> \n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n\tProgram:%s \n", 
				rw2.getUserName(), rw2.getFirstName(), rw2.getLastName(),rw2.getEmail(), rw2.getProgram());
		System.out.println("\n\n=========================================\n\n");
		
		
		// 4. LetterWriter
		System.out.println("\n\n=========================================\n\n");
		LetterWriter lw1 = letterWriterDao.getLetterWriterByUserID(7);
		System.out.format("Reading Writer for userID=7 -> \n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n\tUniversity:%s \n",
			lw1.getUserName(), lw1.getFirstName(), lw1.getLastName(),lw1.getEmail(), lw1.getUniversity());
		System.out.println("\n\n=========================================\n\n");
		
		
		System.out.println("\n\n=========================================\n\n");
		LetterWriter lw2 = letterWriterDao.getLetterWriterByUserID(8);
		System.out.format("Reading Writer for userID=8 -> \n\tUserName:%s \n\tFirstName:%s \n\tLastName:%s \n\tEmail:%s \n\tUniversity:%s \n", 
				lw2.getUserName(), lw2.getFirstName(), lw2.getLastName(),lw2.getEmail(), lw2.getUniversity());
		System.out.println("\n\n=========================================\n\n");
		
		
		// 5. Degree
		System.out.println("\n\n=========================================\n\n");
		List<Degree> degreesByApplicant = degreeDao.getDegreesByApplicant(janeApplicant);
		System.out.println("Degrees of Jane ->");
		for(Degree d : degreesByApplicant) {
			System.out.format("\n \n\tDegreeID:%s \n\tDegreeType:%s \n\tGrantingInstitution:%s \n\tSubject:%s \n\tDateGranted:%s ",d.getDegreeID(), d.getDegreeType(), d.getGrantingInstitution(),d.getSubject(),d.getDateGranted());
		}
		System.out.println("\n\n=========================================\n\n");
		
		
		// 6. RecLetter
		System.out.println("\n\n=========================================\n\n");
		RecLetter rl1 = recLetterDao.getRecLetterByApplicantAndAuthor(janeApplicant, perryLetterWriter);
		System.out.format("Reading RecLetter for Jane Smith Applicant provided by Matthew Perry as Author -> \n\tAuthorID:%s \n\tAuthorName:%s \n\tApplicantID:%s \n\tApplicant Name:%s \n\tDateReceived:%s \n\tBody:%s ",
				rl1.getAuthorID().getUserID(),rl1.getAuthorID().getUserName(), rl1.getApplicant().getUserID(), rl1.getApplicant().getUserName(), rl1.getDateReceived(), rl1.getBody());
		System.out.println("\n\n=========================================\n\n");
		
		
		System.out.println("\n\n=========================================\n\n");
		List<RecLetter>  recLettersByApplicant = recLetterDao.getRecLettersByApplicant(janeApplicant);
		System.out.println("RecLetters of Jane ->");
		for(RecLetter rl : recLettersByApplicant) {
			System.out.format("\n \n\tAuthorID:%s \n\tAuthor Name:%s \n\tApplicantID:%s \n\tDateReceived:%s \n\tBody:%s ",
					rl.getAuthorID().getUserID(), rl.getAuthorID().getUserName() ,rl.getApplicant().getUserID(), rl.getDateReceived(), rl.getBody());
		}
		System.out.println("\n\n=========================================\n\n");
		
		
		// 7. Rating
		System.out.println("\n\n=========================================\n\n");
		List<Rating>  ratings = ratingDao.getRatingByApplicant(janeApplicant);
		System.out.println("Ratings of Jane Smith ->");
		for(Rating r : ratings) {
			System.out.format("\n \n\tReviewerID:%s \n\tReviwer Name:%s \n\tApplicantID:%s \n\tRating:%s ",
					r.getReviewer().getUserID(), r.getReviewer().getUserName(),r.getApplicant().getUserID(), r.getRating());
		}
		System.out.println("\n\n=========================================\n\n");
		
		System.out.println("\n\n=========================================\n\n");
		Rating updatedRating1 = ratingDao.updateRating(janeRating1, 10);
		Rating updatedRating2 = ratingDao.updateRating(janeRating2, 10);
		List<Rating>  updatedRatings = ratingDao.getRatingByApplicant(janeApplicant);
		System.out.println("Updated Ratings of Jane Smith ->");
		for(Rating r : updatedRatings) {
			System.out.format("\n \n\tReviewerID:%s \n\tReviewer Name:%s \n\tApplicantID:%s \n\tRating:%s ",
					r.getReviewer().getUserID(),r.getReviewer().getUserName(), r.getApplicant().getUserID(), r.getRating());
		}
		System.out.println("\n\n=========================================\n\n");
		System.out.println("\n\nSuccessfully Executed the Code.\n\n");
	}
}
