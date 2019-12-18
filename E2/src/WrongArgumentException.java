/* @author Fuad Aliev
 *  02.12.2018
 */
public class WrongArgumentException extends IllegalArgumentException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String getMessage() {
        return "Error: Fehlerhafte Eingabe";
    }

}
