

public class AppController {
    private static final int VALID_MAX_SCORE = 100;
    private static final int VALID_MIN_SCORE = 0;
    private static final int BAN_CAPACITY = 10;

    private Ban _ban;
    private GradeCounter _gradeCounter;

    public Ban ban() {
        return _ban;
    } //ban getter

    public void setBan(Ban newBan) {
        this._ban = newBan;
    } //ban setter

    public GradeCounter gradeCounter() {
        return _gradeCounter;
    } //gradecounter getter

    public void setGradeCounter(GradeCounter newGradeCounter) { //gradecounter stteer
        this._gradeCounter = newGradeCounter;
    }


    private static boolean scoreIsValid(int aScore) { //��ȿ�� Ȯ��
        return (aScore >= AppController.VALID_MIN_SCORE && aScore <= AppController.VALID_MAX_SCORE);
    }

    private static Student inputStudent() { //���� �޽��� ��� �� student ���� �Է�
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

    private void inputAndStoreStudents() { //�л��� �ڷᱸ���� �ִ´�.
        AppView.outputLine("");
        boolean storingAStudentWasSucessful = true;

        while (storingAStudentWasSucessful && AppView.doesContinueToInputStudent()) {
            Student student = this.inputStudent();
            if (!this.ban().add(student)) {
                AppView.outputLine("(���) �Է¿� ������ �ֽ��ϴ�. �б޿� ���̻� �л��� ���� ������ �����ϴ�. ");
                storingAStudentWasSucessful = false;
            }

        }
        AppView.outputLine("! ���� �Է��� ��Ĩ�ϴ�. ");
    }

    private void showStatistics() { //����Լ� ���
        AppView.outputLine("");
        AppView.outputLine("[�б� ���� ���]");

        AppView.outputNumberOfStudents(this.ban().size()); //�л���
        AppView.outputHighestScore(this.ban().highest().score()); //�ְ���
        AppView.outputLowestScore(this.ban().lowest().score());  //������
        AppView.outputAverageScore(this.ban().average());       //��� ����
        AppView.outputNumberOfStudentsAboveAverage(this.ban().numberOfStudentsAboveAverage()); //��� ���� �̻�

    }

    private void showGradeCounts() { //���� �� �л��� ������ ����Ѵ�.
        AppView.outputLine("");
        AppView.outputLine("[���� �� �л� ��]");

        this.setGradeCounter(this.ban().countGrade());
        AppView.outputNumberOfStudentsForGrade('A', this.gradeCounter().numberOfA());
        AppView.outputNumberOfStudentsForGrade('B', this.gradeCounter().numberOfB());
        AppView.outputNumberOfStudentsForGrade('C', this.gradeCounter().numberOfC());
        AppView.outputNumberOfStudentsForGrade('D', this.gradeCounter().numberOfD());
        AppView.outputNumberOfStudentsForGrade('F', this.gradeCounter().numberOfF());
    }

    private void showStudensSortedByScore() { //�л����� ������ ���� ����� �Ѵ�.
        AppView.outputLine("");
        AppView.outputLine("[�л����� ���� �� ���]");

        this.ban().sortByScore();

        Iterator<Student> iterator = this.ban().iterator(); //Iterator �� ����� �Ͽ� ����� �Ѵ�.
        Student student = null;
        while (iterator.hasNext()) {
            student = iterator.next();
            AppView.outputScore(student.score());
        }
    }


    public void run() { //����
        AppView.outputLine("");
        AppView.outputLine("<<< �б� ���� ó���� �����մϴ�. >>>");

        this.setBan(new Ban(AppController.BAN_CAPACITY)); //ban�� ����
        this.inputAndStoreStudents(); //�л����� ���� �Է�
        if (this.ban().isEmpty()) {  // ����ִ��� Ȯ��
            AppView.outputLine("");
            AppView.outputLine("(���) �Էµ� ������ �����ϴ�. ");

        } else {
            this.showStatistics(); //����Լ� ���
            this.showGradeCounts(); //���� �� �л� �� ���
            this.showStudensSortedByScore(); //�л����� ���� �� ���
        }
        AppView.outputLine("");
        AppView.outputLine("<<< �б� ���� ó���� �����մϴ�. ");


    }


}

