/* Manages ID for the controllers and fragments in Main Activity
 * Note: At some point, we will have multiple applications and UCSD might not be
 * the only college we support. At that point, we will have to rework the ids and naming
 * conventions. It will depend entirely on what apps each college has. We may
 * have to copy the structure of the backend for this part.
 * */
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
	public static final String registerID = "UCSD Register";

	public static final String collegeTickrURL = "http://www.collegetickr.com";
	
	public static final int guardianNumericID = 101;
	public static final int TVNumericID = 102;
	public static final int complimentsNumericID = 103;
	public static final int confessionsNumericID = 104;
	public static final int dealsNumericID = 105;
	public static final int jobsNumericID = 106;
	public static final int signupNumericID = 107;
	public static final int loginNumericID = 108;
	public static final int registerNumericID = 109;
	public static final int POST_SQUARE_SIZE = 400;
	public static final String SELECTIONID = "selection id"; 
	
	public static final String loggedInSharedPreferenceID = "LoggedIn";
	public static final String universitySharedPreferenceID = "UniversityOfUser";
	public static final String userNameSharedPreferenceID = "UserName";
	public static final String passwordSharedPreferenceID = "Password";
	
	public static final int previewUploadThumbnailSize = 100;

	//Confessions/Compliments identifiers
	public static final String EDIT_TEXT_BUNDLE_TAG = "EDIT_TEXT_BUNDLE_TAG";
	public static final String IMAGE_PREVIEW_BUNDLE_TAG = "IMAGE_PREVIEW_BUNDLE_TAG";
	public static final String EXIT_IMAGE_PREVIEW_BUTTON_VISIBILITY_BUNDLE_TAG = "EXIT_IMAGE_PREVIEW_BUTTON_VISIBILITY_BUNDLE_TAG";
	public static final String IMAGE_PREVIEW_BUTTON_VISIBILITY_BUNDLE_TAG = "IMAGE_PREVIEW_BUTTON_VISIBILITY_BUNDLE_TAG";
	public static final String LIST_OF_COMPLIMENTS_CONFESSIONS_TAG = "LIST_OF_COMPLIMENTS_CONFESSIONS_TAG";
	//ActivityResults
	public static final int RESULT_LOAD_IMAGE = "RESULT_LOAD_IMAGE".hashCode();
	public static final String POPUP_LOGIN_FRAGMENT_TAG = "popupLoginFragmentTag";
	
	//POST tags
	public static final String CONFESSIONSURL = "http://collegetickr.com/ucsd/confessions";
	public static final String COMPLIMENTSURL = "http://collegetickr.com/ucsd/compliments";
	public static final String commentPostHTTP = "content[content]";
	public static final String picturePostHTTP = "content[image]";
	
	//upload/commenting tags
	public static final String misingComment = "You have a blank textbox!";
	public static final String invalidPicture = "Your picture was invalid. Please reselect.";
	public static final String notExecutingiD = "invalidParamsToBegin";
	
	public static boolean LOAD_ALL_AT_ONCE = true;
}