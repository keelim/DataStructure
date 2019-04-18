

public class AppController {
    private static final int VALID_MAX_SCORE = 100;
    private static final int VALID_MIN_SCORE = 0;
    private static final int BAN_CAPACITY = 10;

    private Ban _ban;
    private GradeCounter _gradeCounter;

    public Ban ban() {
        return _ban;
    }

    public void setBan(Ban newBan) {
        this._ban = newBan;
    }

    public GradeCounter gradeCounter() {
        return _gradeCounter;
    }

    public void setGradeCounter(GradeCounter newGradeCounter) {
        this._gradeCounter = newGradeCounter;
    }


    private static boolean scoreIsValid(int aScore) {
        return (aScore >= AppController.VALID_MIN_SCORE && aScore <= AppController.VALID_MAX_SCORE);
    }

    private static Student inputStudent() {
        int score = AppView.inputScore();
        while (!AppController.scoreIsValid(score)) {
            AppView.outputLine("[����]" +
                    AppController.VALID_MIN_SCORE + "���� �۰ų�" +
                    AppController.VALID_MAX_SCORE + "���� Ŀ�� , �������� ������ �ƴմϴ�. ");
            score = AppView.inputScore();
        }

        Student student = new Student();
        student.setScore(score);
        return student;

    }

    private void inputAndStoreStudents() {
        AppView.outputLine("");
        boolean storingAStudentWasSucessful = true;
        Student student = this.inputStudent();
        while (storingAStudentWasSucessful && AppView.doesContinueToInputStudent()) {
            if (!this.ban().add(student)) { //todo student ����?
                AppView.outputLine("(���) �Է¿� ������ �ֽ��ϴ�. �б޿� ���̻� �л��� ���� ������ �����ϴ�. ");
                storingAStudentWasSucessful = false;
            }

        }
        AppView.outputLine("! ���� �Է��� ��Ĩ�ϴ�. ");
    }

    private void showStatistics() { //todo ������ �� ��
        AppView.outputLine("");
        AppView.outputLine("[�б� ���� ���]");

        AppView.outputNumberOfStudents(this.ban().size());
        AppView.outputHighestScore(this.ban().highest().score());
        AppView.outputLowestScore(this.ban().lowest().score());
        AppView.outputAverageScore(this.ban().average());
        AppView.outputNumberOfStudentsAboveAverage(this.ban().numberOfStudentsAboveAverage());

    }

    private void showGradeCounts() {
        AppView.outputLine("");
        AppView.outputLine("[���� �� �л� ��]");

        this.setGradeCounter(this.ban().countGrade()); //todo �ʵ尡 �ϳ� ���ִµ�
        AppView.outputNumberOfStudentsForGrade('A', this.gradeCounter().numberOfA());
        AppView.outputNumberOfStudentsForGrade('B', this.gradeCounter().numberOfB());
        AppView.outputNumberOfStudentsForGrade('C', this.gradeCounter().numberOfC());
        AppView.outputNumberOfStudentsForGrade('D', this.gradeCounter().numberOfD());
        AppView.outputNumberOfStudentsForGrade('F', this.gradeCounter().numberOfF());
    }

    private void showStudensSortedByScore() {
        AppView.outputLine("");
        AppView.outputLine("[�л����� ������ ���]");

        this.ban().sortByScore();

        Iterator<Student> iterator = this.ban().iterator();
        Student student = null;
        while (iterator.hasNext()) {
            student = iterator.next();
            AppView.outputScore(student.score());
        }

    }


    public void run() {
        AppView.outputLine("");
        AppView.outputLine("<<< �б� ���� ó���� �����մϴ�. >>>");

        this.setBan(new Ban(AppController.BAN_CAPACITY));
        this.inputAndStoreStudents();
        if (this.ban().isEmpty()) {
            AppView.outputLine("");


        } else {
            this.showStatistics();
            this.showGradeCounts();
            this.showStudensSortedByScore();
        }
        AppView.outputLine("");
        AppView.outputLine("<<< �б� ���� ó���� �����մϴ�. ");


    }


}

