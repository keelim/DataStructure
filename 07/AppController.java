public class AppController {
    private static final int STACK_CAPACITY = 10; // ����� ������
    private ArrayList<Character> _stack;
    private int _inputChars;   // �Էµ� ������ ����
    private int _pushedChars; // ���Ե� ������ ����
    private int _ignoredChars; // ���õ� ������ ����


    public ArrayList<Character> stack() { //constructor
        return _stack;
    }

    public void setStack(ArrayList<Character> _stack) { //setter
        this._stack = _stack;
    }

    public int inputChars() { //getter
        return _inputChars;
    }

    public void setInputChars(int _inputChars) {//setter
        this._inputChars = _inputChars;
    }

    public int pushedChars() { //getter
        return _pushedChars;
    }

    public void setPushedChars(int _pushedChars) { //setter
        this._pushedChars = _pushedChars;
    }

    public int ignoredChars() { //getter
        return _ignoredChars;
    }

    public void setIgnoredChars(int _ignoredChars) { //setter
        this._ignoredChars = _ignoredChars;
    }

    public AppController() { //constructor
        this.setStack(new ArrayList<Character>(AppController.STACK_CAPACITY));
        this.setInputChars(0);
        this.setPushedChars(0);
        this.setIgnoredChars(0);
    }

    // Ƚ�� ���
    private void countInputChar() {
        this.setInputChars(this.inputChars() + 1);
    }

    private void countIgnoredChar() {
        this.setIgnoredChars(this.ignoredChars() + 1);
    }

    private void countPushedChar() {
        this.setPushedChars(this.pushedChars() + 1);
    }

    // ���� ���� ����
    private void pushToStack(char aCharForPush) {
        if (this.stack().isFull()) {
            AppView.outputLine("(����) ������ ������ �� �̻� ���� �� �����ϴ�. ");
        } else {
            if (this.stack().push(Character.valueOf(aCharForPush))) {
                AppView.outputLine("[Push]  ���Ե� ���Ҵ� " + aCharForPush + "�Դϴ�. ");
            } else {
                AppView.outputLine("(����) ���ÿ� �ִ� ���� ������ �߻��Ͽ����ϴ�. ");
            }

        }
    }

    private void popOne() {
        if (this.stack().isEmpty()) {
            AppView.outputLine("[Pop.Empty] ���ÿ� ������ ���Ұ� �����ϴ�. ");

        } else {
            Character poppedChar = this.stack().pop();
            if (poppedChar == null) {
                AppView.outputLine("(����) ���ÿ��� �����ϴ� ���ȿ� ������ �߻��Ͽ����ϴ�. ");
            } else {
                AppView.outputLine("[Pop] ������ ���Ҵ� '" + poppedChar + "' �Դϴ�. ");
            }
        }
    }

    private void popN(int numberOfCharsToBePopped) {
        if (numberOfCharsToBePopped == 0) {
            AppView.outputLine("[Pops] ������ ������ ������ 0 �� �Դϴ�. ");
        } else {
            int count = 0;
            while (count < numberOfCharsToBePopped && (!this.stack().isEmpty())) {
                Character poppedChar = this.stack().pop();
                if (poppedChar == null) {
                    AppView.outputLine("(����) ���ÿ��� �����ϴ� ���ȿ� ������ �߻��Ͽ����ϴ�. ");

                } else {
                    AppView.outputLine("[Pops] ������ ���Ҵ� '" + poppedChar + "' �Դϴ�. ");
                }
                count++;
            }
            if (count < numberOfCharsToBePopped) {
                AppView.outputLine("[Pops.Empty] ���ÿ� ���̻� ������ ���Ұ� �����ϴ�. ");
            }
        }
    }

    private void quitStackProcessing() {
        AppView.outputLine("");
        AppView.outputLine("<������ ���� ����� �����մϴ�.>");
        this.showAllFromBottom();
        this.popN(this.stack().size());
    }

    // ��� ����
    private void showAllFromBottom() {
        AppView.output("[Stack] <Bottom> ");
        for (int order = 0; order < this.stack().size(); order++) {
            AppView.output(this.stack().elementAt(order).toString() + " ");
        }
        AppView.outputLine(" <Top>");
    }

    private void showAllFromTop() {
        AppView.output("[Stack] <Top> ");
        for (int order = this.stack().size() - 1; order >= 0; order--) {
            AppView.output(this.stack().elementAt(order).toString() + " ");
        }
        AppView.outputLine(" <Bottom>");

    }

    private void showTopElement() { //Top Element���
        if (this.stack().isEmpty()) {
            AppView.outputLine("[Pop.Empty] ���ÿ� ������ ���Ұ� �����ϴ�. ");
        } else{
            AppView.outputLine("[Top] ������ Top ���Ҵ� '" + this.stack().peek() + "' �Դϴ�. ");
        }
    }

    private void showStackSize() {
        AppView.outputLine("Stack���� ���� " + this.stack().size() + " ���� ���Ұ� �ֽ��ϴ�.");
    }

    private void showStatistics() { //����Լ� ���
        AppView.outputLine("");
        AppView.outputLine("<���� ��� ���>");
        AppView.outputLine("- �Էµ� ���ڴ� " + this.inputChars() + "�� �Դϴ�.");

        AppView.outputLine("- ���� ó���� ���ڴ� " + (this.inputChars() - this.ignoredChars()) + "�� �Դϴ�.");
        AppView.outputLine("- ���õ� ���ڴ�" + this.ignoredChars() + "�� �Դϴ�. ");
        AppView.outputLine("- ���Ե� ���ڴ�  " + this.pushedChars() + "�� �Դϴ�. ");


    }

    // �Է� ����
    private char inputChar() {
        AppView.outputLine("? ���ڸ� �Է��Ͻÿ�: ");
        return AppView.inputChar();
    }


    public void run() {
        AppView.outputLine("<<< ���� ��� Ȯ�� ���α׷��� �����մϴ�. >>>");
        AppView.outputLine("");

        char input = this.inputChar();

        while (input != '!') {
            this.countInputChar();
            if (Character.isAlphabetic(input)) {//���ĺ� �˻�
                this.pushToStack(input);
                this.countPushedChar();
            } else if (Character.isDigit(input)) { //���� ���� �˻�
                this.popN(Character.getNumericValue(input));
            } else if (input == '-') {
                this.popOne();
            } else if (input == '#') {
                this.showStackSize();
            } else if (input == '/') {
                this.showAllFromBottom();
            } else if (input == '\\') {
                this.showAllFromTop();
            } else if (input == '^') {
                this.showTopElement();
            } else {
                AppView.outputLine("[ignore] �ǹ� ���� ���ڰ� �ԷµǾ����ϴ�. ");
                this.countIgnoredChar();
            }
            input = this.inputChar();

        }
        this.quitStackProcessing();

        this.showStatistics();
        AppView.outputLine("");
        AppView.outputLine("<<< ���� ��� Ȯ�� ���α׷��� �����մϴ�. >>>");

    }
}
