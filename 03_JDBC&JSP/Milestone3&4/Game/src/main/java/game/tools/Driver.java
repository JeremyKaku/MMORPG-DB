package game.tools;

import game.dal.*;
import game.model.*;

import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
import java.time.LocalTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.lang.reflect.Field;

/**
 * main() runner, used for the app demo.
 * 
 * Instructions: 1. Create a new MySQL schema and then run the CREATE TABLE
 * statements from lecture: http://goo.gl/86a11H. 2. Update ConnectionManager
 * with the correct user, password, and schema.
 */
public class Driver {

	public static void main(String[] args) throws SQLException, IllegalAccessException {
		// DAO instances.
//		ApplicantDao applicantDao = ApplicantDao.getInstance();
//		ReviewerDao reviewerDao = ReviewerDao.getInstance();
//		LetterWriterDao letterWriterDao = LetterWriterDao.getInstance();
//		RatingDao ratingDao = RatingDao.getInstance();
//		DegreeDao degreeDao = DegreeDao.getInstance();
//		RecLetterDao recLetterDao = RecLetterDao.getInstance();
//		Date date = new Date();
		insertData();
		System.out.print("================INSERT   START================\n\n");
		// User
//		System.out.format("*Inserting User: Start Time:%s \n", LocalTime.now());
//		Player player = new Player("Jing Guo", "guo.jing2@northeastern.edu", hashPasswordSHA2("password123"));
//		player = playerDao.create(player);
//		System.out.format("User: PlayerName:%s FirstName:%s LastName:%s Email:%s \n", u1.getUserName(),
//		u1.getFirstName(), u1.getLastName(), u1.getEmail());
//		User user1 = new User("Xue Han", "xue", "han", "xuehan@gmail.com");
//		user1 = userDao.create(user1);
//		User user2 = new User("Richard Cobbe", "richard", "cobbe", "richardcobbe@gmail.com");
//		user2 = userDao.create(user2);
//		System.out.format("*Inserting User: End Time:%s \n\n", LocalTime.now());
//
//		// Applicant
//		System.out.format("*Inserting Applicant: Start Time:%s \n", LocalTime.now());
//		Applicant applicant = new Applicant(4, "Bob", "bob", "lee", "lee@gmail.com", "good essay",
//				Applicant.Program.masters);
//		applicant = applicantDao.create(applicant);
//		Applicant applicant1 = new Applicant(5, "Ken", "ken", "james", "ken@gmail.com", "nice essay",
//				Applicant.Program.phd);
//		applicant1 = applicantDao.create(applicant1);
//		Applicant applicant2 = new Applicant(6, "Seven", "steven", "jack", "jack@gmail.com", "not bad essay",
//				Applicant.Program.phd);
//		System.out.format("*Inserting Applicant: End Time:%s \n\n", LocalTime.now());
//		applicant2 = applicantDao.create(applicant2);
//
//		// Reviewer
//		System.out.format("*Inserting Reviewer: Start Time:%s \n", LocalTime.now());
//		Reviewer reviewer = new Reviewer(7, "Jeremy", "jeremy", "lin", "lin@gmail.com", Reviewer.Program.phd);
//		reviewer = reviewerDao.create(reviewer);
//		Reviewer reviewer1 = new Reviewer(8, "Soy", "soy", "san", "san@gmail.com", Reviewer.Program.masters);
//		reviewer1 = reviewerDao.create(reviewer1);
//		Reviewer reviewer2 = new Reviewer(9, "Winter", "winter", "wang", "wang@gmail.com", Reviewer.Program.phd);
//		reviewer2 = reviewerDao.create(reviewer2);
//		System.out.format("*Inserting Reviewer: End Time:%s \n\n", LocalTime.now());
//
//		// LetterWriter
//		System.out.format("*Inserting LetterWriter: Start Time:%s \n", LocalTime.now());
//		LetterWriter letterWriter = new LetterWriter(10, "Summer", "summer", "joe", "joe@gmail.com", "NEU");
//		letterWriter = letterWriterDao.create(letterWriter);
//		LetterWriter letterWriter1 = new LetterWriter(11, "Rick", "rick", "koe", "koe@gmail.com", "UW");
//		letterWriter1 = letterWriterDao.create(letterWriter1);
//		LetterWriter letterWriter2 = new LetterWriter(12, "Morty", "morty", "anna", "anna@gmail.com", "NYU");
//		letterWriter2 = letterWriterDao.create(letterWriter2);
//		System.out.format("*Inserting LetterWriter: End Time:%s \n\n", LocalTime.now());
//
//		// Degree
//		System.out.format("*Inserting Degree: Start Time:%s \n", LocalTime.now());
//		Degree degree = new Degree(applicant, Degree.DegreeType.masters, "COE", "IS", date);
//		degree = degreeDao.create(degree);
//		Degree degree1 = new Degree(applicant1, Degree.DegreeType.masters, "Khoury", "CSA", date);
//		degree1 = degreeDao.create(degree1);
//		Degree degree2 = new Degree(applicant1, Degree.DegreeType.phd, "Khoury", "CS", date);
//		degree2 = degreeDao.create(degree2);
//		System.out.format("*Inserting Degree: End Time:%s \n\n", LocalTime.now());
//
//		// RecLetter
//		System.out.format("*Inserting RecLetter: Start Time:%s \n", LocalTime.now());
//		RecLetter recLetter = new RecLetter(applicant, letterWriter, date, "letter body");
//		recLetter = recLetterDao.create(recLetter);
//		RecLetter recLetter1 = new RecLetter(applicant, letterWriter1, date, "letter body");
//		recLetter1 = recLetterDao.create(recLetter1);
//		RecLetter recLetter2 = new RecLetter(applicant, letterWriter2, date, "letter body");
//		recLetter2 = recLetterDao.create(recLetter2);
//		System.out.format("*Inserting RecLetter: End Time:%s \n\n", LocalTime.now());
//
//		// Rating
//		System.out.format("*Inserting Rating: Start Time:%s \n", LocalTime.now());
//		Rating rating = new Rating(reviewer, applicant1, 90);
//		rating = ratingDao.create(rating);
//		Rating rating1 = new Rating(reviewer1, applicant1, 87);
//		rating1 = ratingDao.create(rating1);
//		Rating rating2 = new Rating(reviewer2, applicant1, 99);
//		rating2 = ratingDao.create(rating2);
//		System.out.format("*Inserting Rating: Start Time:%s \n\n", LocalTime.now());
//		System.out.print("================INSERT   END==================\n\n");
//
//		/****************************************
//		 * READ
//		 ****************************************/
//		System.out.print("================READ     START================\n\n");
//		// User
//		System.out.format("*Reading User: Start Time:%s \n", LocalTime.now());
//		User u1 = userDao.getUserByUserID(1);
//		System.out.format("Reading User: UserName:%s FirstName:%s LastName:%s Email:%s \n", u1.getUserName(),
//				u1.getFirstName(), u1.getLastName(), u1.getEmail());
//		System.out.format("*Reading User: End Time:%s \n\n", LocalTime.now());
//
//		// Applicant
//		System.out.format("*Reading Applicant: Start Time:%s \n", LocalTime.now());
//		Applicant a1 = applicantDao.getApplicantByUserID(4);
//		System.out.format("Reading Applicant: UserName:%s FirstName:%s LastName:%s Email:%s Program:%s Essay:%s \n",
//				a1.getUserName(), a1.getFirstName(), a1.getLastName(), a1.getEmail(), a1.getProgram(), a1.getEssay());
//		List<Applicant> aList1 = applicantDao.getApplicantsByProgram(Applicant.Program.phd);
//		for (Applicant a : aList1) {
//			System.out.format(
//					"Looping Applicant: UserName:%s FirstName:%s LastName:%s  Email:%s Program:%s Essay:%s  \n",
//					a.getUserName(), a.getFirstName(), a.getLastName(), a.getEmail(), a.getProgram(), a.getEssay());
//		}
//		System.out.format("*Reading Applicant: End Time:%s \n\n", LocalTime.now());
//
//		// Reviewer
//		System.out.format("*Reading Reviewer: Start Time:%s \n", LocalTime.now());
//		Reviewer r1 = reviewerDao.getReviewerByUserID(7);
//		System.out.format("Reading Reviewer: UserName:%s FirstName:%s LastName:%s Email:%s Program:%s \n",
//				r1.getUserName(), r1.getFirstName(), r1.getLastName(), r1.getEmail(), r1.getProgram());
//		System.out.format("*Reading Reviewer: End Time:%s \n\n", LocalTime.now());
//
//		// LetterWriter
//		System.out.format("*Reading LetterWriter: Start Time:%s \n", LocalTime.now());
//		LetterWriter l1 = letterWriterDao.getLetterWriterByUserID(10);
//		System.out.format("Reading LetterWriter: UserName:%s FirstName:%s LastName:%s Email:%s University:%s \n",
//				l1.getUserName(), l1.getFirstName(), l1.getLastName(), l1.getEmail(), l1.getUniversity());
//		System.out.format("*Reading LetterWriter: End Time:%s \n\n", LocalTime.now());
//
//		// Degree
//		System.out.format("*Reading Degree: Start Time:%s \n", LocalTime.now());
//		List<Degree> dList1 = degreeDao.getDegreesByApplicant(applicant1);
//		for (Degree a : dList1) {
//			System.out.format(
//					"Looping Degree: ApplicantName:%s DegreeType:%s GrantingInstitution:%s  Subject:%s  DateGranted:%s  \n",
//					a.getApplicant().getUserName(), a.getDegreeType(), a.getGrantingInstitution(), a.getSubject(),
//					a.getDateGranted());
//		}
//		System.out.format("*Reading Degree: End Time:%s \n\n", LocalTime.now());
//
//		// RecLetter
//		System.out.format("*Reading RecLetter: Start Time:%s \n", LocalTime.now());
//		RecLetter rec1 = recLetterDao.getRecLetterByApplicantAndAuthor(applicant, letterWriter);
//		System.out.format("Reading RecLetter: ApplicantName:%s LetterWriterName:%s DateReceived:%s Body:%s \n",
//				rec1.getApplicant().getUserName(), rec1.getLetterWriter().getUserName(), rec1.getDateReceived(),
//				rec1.getBody());
//		List<RecLetter> recList1 = recLetterDao.getRecLettersByApplicant(applicant);
//		for (RecLetter a : recList1) {
//			System.out.format("Looping RecLetter: ApplicantName:%s LetterWriterName:%s DateReceived:%s  Body:%s  \n",
//					a.getApplicant().getUserName(), a.getLetterWriter().getUserName(), a.getDateReceived(),
//					a.getBody());
//		}
//		System.out.format("*Reading RecLetter: End Time:%s \n\n", LocalTime.now());
//
//		// Rating
//		System.out.format("*Reading Rating: Start Time:%s \n", LocalTime.now());
//		List<Rating> raList1 = ratingDao.getRatingByApplicant(applicant1);
//		for (Rating a : raList1) {
//			System.out.format("Looping Rating: ReviewerName:%s ApplicantName:%s Rating:%s \n",
//					a.getReviewer().getUserName(), a.getApplicant().getUserName(), a.getRating());
//		}
//		System.out.format("*Reading Rating: End Time:%s \n\n", LocalTime.now());
//		System.out.print("================READ     END===================\n\n");
//
//		System.out.print("================DELETE   START=================\n\n");
//		// User
//		System.out.format("*Deleting User: Start Time:%s \n", LocalTime.now());
//		userDao.delete(user);
//		System.out.format("*Deleting User: End Time:%s \n\n", LocalTime.now());
//
//		// Applicant
//		System.out.format("*Deleting Applicant: Start Time:%s \n", LocalTime.now());
//		userDao.delete(applicant);
//		System.out.format("*Deleting Applicant: End Time:%s \n\n", LocalTime.now());
//		System.out.print("================DELETE   END===================\n\n");
//
//		System.out.print("================UPDATE   START=================\n\n");
//		System.out.format("*Updating Applicant: Start Time:%s \n", LocalTime.now());
//		Rating update = ratingDao.updateRating(rating, 100);
//		System.out.format("Updating update: ReviewName:%s ApplicantName:%s NewRating:%s \n",
//				update.getApplicant().getUserName(), update.getReviewer().getUserName(), update.getRating());
//		System.out.format("*Updating Applicant: End Time:%s \n\n", LocalTime.now());
//		System.out.print("================UPDATE   END===================\n\n");

	}

	/**
	 * This method insert data into each table.
	 * 
	 * @param Nothing
	 * @return Nothing
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 */
	private static void insertData() throws SQLException, IllegalAccessException {
		PlayerDao playerDao = PlayerDao.getInstance();
		System.out.print("================INSERT   START================\n\n");
		// User
		System.out.format("*Inserting User: Start Time:%s \n", LocalTime.now());
		Player player = new Player("Jing Guo", "guo.jing2@northeastern.edu", hashPasswordSHA2("password123"));
		player = playerDao.create(player);
		System.out.format("*player inserted :%s \n\n", LocalTime.now());
		
//		System.out.format("User: PlayerName:%s FirstName:%s LastName:%s Email:%s \n", player.getUserName(),
//				player.getFirstName(), player.getLastName(), player.getEmail());
//		User user1 = new User("Xue Han", "xue", "han", "xuehan@gmail.com");
//		user1 = userDao.create(user1);
//		User user2 = new User("Richard Cobbe", "richard", "cobbe", "richardcobbe@gmail.com");
//		user2 = userDao.create(user2);
		System.out.format("*Inserting User: End Time:%s \n\n", LocalTime.now());
	}

	// SHA2 Hash
	private static String hashPasswordSHA2(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(password.getBytes());

			StringBuilder hexString = new StringBuilder();
			for (byte hashByte : hashBytes) {
				String hex = Integer.toHexString(0xff & hashByte);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void printObjectFields(Object obj) throws IllegalAccessException {
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println(field.getName() + " = " + field.get(obj));
		}
	}
}
