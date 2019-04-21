public class FsmExcepton extends Exception {
    private final String messageException;

    FsmExcepton(String message) {
        this.messageException = message;
    }

    @Override
    public String getMessage() {
        return messageException;
    }
}
