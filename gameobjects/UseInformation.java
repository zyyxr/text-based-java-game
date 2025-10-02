package gameobjects;

/**
 * Represents information about how an object can be used in the game.
 * 
 * <p>
 * This class stores details about the usage of an object, such as whether it has
 * already been used, the type of action it performs, the target of the action,
 * the result of the action, and any associated message.
 * </p>
 */
public class UseInformation {
        boolean isUsed;
    String action;
    String target;
    String result;
    String message;

    public UseInformation(boolean isUsed, String action, String target, String result, String message) {
        this.isUsed = isUsed;
        this.action = action;
        this.target = target;
        this.result = result;
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }

    public String getTarget() {
        return target;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }



    /**
     * Returns a string representation of the usage information, including all attributes.
     *
     * @return a string describing the usage information
     */
    @Override
    public String toString() {
        return "UseInformation{" +
                "isUsed=" + isUsed +
                ", action='" + action + '\'' +
                ", target='" + target + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
