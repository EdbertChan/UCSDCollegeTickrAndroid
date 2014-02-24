/* Manages ID for the controllers and fragments in Main Activity*/
package collegetickr.library;
public class  IdentifiersList{
	public static final String signupID = "Signup";
	public static final String loginID = "Login";
	public static final String guardianID = "UCSD Guardian";
	public static final String TVID = "Triton TV";
	public static final String complimentsID = "UCSD Compliment";
	public static final String confessionsID = "UCSD Confession";
	public static final String dealsID = "UCSD Deals";
	public static final String jobsID = "UCSD Jobs & Internships";
	
	
	public static final int guardianNumericID = 101;
	public static final int TVNumericID = 102;
	public static final int complimentsNumericID = 103;
	public static final int confessionsNumericID = 104;
	public static final int dealsNumericID = 105;
	public static final int jobsNumericID = 106;
	public static final int signupNumericID = 107;
	public static final int loginNumericID = 108;

	
	public static final String SELECTIONID = "selection id"; 
	
	//POST tags
	public static final String CONFESSIONSURL = "http://collegetickr.com/ucsd/confessions";
	public static final String COMPLIMENTSURL = "http://collegetickr.com/ucsd/compliments";
	public static final String commentPostHTTP = "content[content]";
	public static final String picturePostHTTP = "content[image]";
	
	//upload/commenting tags
	public static final String misingComment = "You have a blank textbox!";
	public static final String invalidPicture = "Your picture was invalid. Please reselect.";
	public static final String notExecutingiD = "invalidParamsToBegin";
}