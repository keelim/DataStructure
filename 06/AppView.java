import java.util.Scanner;

public class AppView {
    private static Scanner sc = new Scanner(System.in);

    public static int inputScore() { //���� �Է�
        while (true) {
            try {
                AppView.output("- ������ �Է��Ͻÿ� (0..100): ");
                int score = AppView.inputInt();
                return score;
            } catch (NumberFormatException e) {
                AppView.outputLine("(����) ������ �Էµ��� �ʾҽ��ϴ�.");
            }
        }

    }

    private static int inputInt() throws NumberFormatException { //���� �Է� --> inputScore �� �ѱ��.
        return Integer.parseInt(AppView.sc.nextLine());
    }

    public static void output(String aMessage) {
        System.out.println(aMessage);

    } //�޽��� ���

    public static void outputLine(String aMessage) { //�޽��� ���
        System.out.println(aMessage);

    }


    public static void outputScore(int aScore) {
        System.out.println("���� " + aScore + "�Դϴ�. ");
    } //���� ���


    public static void outputNumberOfStudents(int aNumberOfStudents) {
        //�б� �л� �� ���
        System.out.println("�б� �л� ��: " + aNumberOfStudents);
    }

    public static void outputHighestScore(int aScore) {
        //�б� �ְ� ���� ���
        System.out.println("�б� �ְ� ���� : " + aScore);
    }

    public static void outputLowestScore(int aScore) {
        //������ ���
        System.out.println("�б� ���� ����: " + aScore);
    }


    public static void outputAverageScore(double anAverageScore) {
        //��հ��� ���
        System.out.println("�б� ���: " + anAverageScore);
    }


    public static void outputNumberOfStudentsAboveAverage(int aNumberOfStudents) {
        //��� �̻��� �л� �� ���
        System.out.println("��� �̻��� �л� �� : " + aNumberOfStudents);

    }


    public static void outputNumberOfStudentsForGrade(char aGrade, int aNumberOfStudents) { //������ �л� �� ���
        System.out.println(aGrade + " ������ �л� ���� " + aNumberOfStudents + "�Դϴ�. ");

    }


    public static void outputStudentInfo(int aScore) {
        //�л����� ���� ���
        System.out.println("����:  " + aScore);
    }


    public static boolean doesContinueToInputStudent() {
        AppView.output("������ �Է��Ϸ��� 'Y�� �Ǵ� ��y�� ��, �����Ϸ��� �ٸ� �ƹ� Ű�� ġ�ÿ�: ");

        String line = null;
        do {
            line = AppView.sc.nextLine();
        } while (line.equals(""));

        char answer = line.charAt(0);
        return ((answer == 'y') || (answer == 'Y'));
    }


}
