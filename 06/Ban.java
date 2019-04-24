public class Ban extends UnSortedArrayList<Student> {

    private static char scoreToGrade(int aScore) {//�־��� aScore �� �ش��ϴ� ������ ��´�.
        if (aScore >= 90) {
            return 'A';
        } else if (aScore >= 80) {
            return 'B';
        } else if (aScore >= 70) {
            return 'C';
        } else if (aScore >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public Ban(int givenCapacity) {
        super(givenCapacity);
    } //constructor

    public Ban() {
        super();
    }

    public Student lowest() { //���� ���� ������ ��������� ����
        if (this.isEmpty()) {
            return null;
        } else {
            return this.lowestRecursively(0, this.size() - 1); //recursion
        }
    }

    private Student lowestRecursively(int left, int right) {
        if (left == right) { //����
            return this.elementAt(left);
        } else {
            Student lowestFromRight = lowestRecursively(left + 1, right);
            if (lowestFromRight.compareTo(this.elementAt(left)) <= 0) {
                return lowestFromRight;
            } else {
                return this.elementAt(left);
            }
        }

    }

    //������ ���� ���� �л��� ��´�.
    public Student highest() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.highestRecursively(0, this.size() - 1);
        }

    }

    private Student highestRecursively(int left, int right) {
        if (left == right) { //����
            return this.elementAt(left);
        } else {
            Student rightFromRights = highestRecursively(left + 1, right);
            if (rightFromRights.compareTo(this.elementAt(left)) > 0) {
                return rightFromRights;
            } else {
                return this.elementAt(left);
            }
        }
    }


    public int sum() { //����� ����
        if (this.isEmpty()) {
            return 0;
        } else {
            return this.sumOfScoresRecursively(0, this.size() - 1);
        }
    }

    private int sumOfScoresRecursively(int left, int right) { //������ ����� ���
        int mid = (left + right) / 2;

        if (left == right) {
            return this.elementAt(left).score();

        } else {

            int leftSum = this.sumOfScoresRecursively(left, mid);
            int rightSum = this.sumOfScoresRecursively(mid + 1, right);
            return (leftSum + rightSum);
        }
    }//�б��� �л����� ������ �հ踦 ��´�.


    public double average() {     //�б��� ���� ��� ������ ��´�
        if (this.isEmpty()) {
            return 0;
        } else {
            return ((double) this.sum() / ((double) this.size()));
        }
    }

    public void sortByScore() {
        if (this.size() > 1) {
            int maxLoc = 0;
            for (int i = 1; i < this.size(); i++) { //?
                if (this.elementAt(i).score() > this.elementAt(maxLoc).score()) {
                    maxLoc = i;
                }
            }
            this.swap(maxLoc, this.size() - 1);
            this.quicksortRecursively(0, this.size() - 2);
        }
    }

    private void quicksortRecursively(int left, int right) {
        if (left < right) {
            int mid = this.partition(left, right - 1); //partition method�� ���Ͽ� �߰� �� ����
            this.quicksortRecursively(left, mid - 1); //�߰� ����
            this.quicksortRecursively(mid + 1, right); //�߰� ����
        }

    }

    private int partition(int left, int right) {
        int pivot = left; //�Ǻ� ���� ���Ѵ�.
        int toRight = left;
        int toLeft = right + 1;
        do {
            do {
                toRight++;
            } while (this.elementAt(toRight).score() < this.elementAt(pivot).score()); //�Ǻ����� �������� �����δ�.
            do {
                toLeft--;
            } while (this.elementAt(toLeft).score() > this.elementAt(pivot).score()); //�Ǻ����� �������� �����δ�.

            if (toRight < toLeft) { //toLeft �� ũ�� swap
                this.swap(toRight, toLeft);
            }
        } while (toRight < toLeft);
        this.swap(left, toLeft); //left �� toleft swap
        return toLeft;
    }

    private void swap(int p, int q) {
        Student temp = this.elementAt(p);
        this.setElementAt(p, this.elementAt(q));
        this.setElementAt(q, temp);

    }

    //�б��� �л����� ���� ������ �����Ѵ�.


    public int numberOfStudentsAboveAverage() {
        double average = this.average();
        int numberOfStudentAboveAverage = 0;

        Iterator<Student> iterator = this.iterator(); //iterator�� ����Ͽ� ����
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.score() >= average) {
                numberOfStudentAboveAverage++;
            }
        }
        return numberOfStudentAboveAverage;
    }

    public GradeCounter countGrade() { //�л����� ���� ����.
        // �б��� ������ �л����� ���� �ϰ�, �� ����� ������ �ִ� GradeCounter ��ü�� ��´�
        GradeCounter counter = new GradeCounter();
        for (int i = 0; i < this.size(); i++) {
            char count = Ban.scoreToGrade(this.elementAt(i).score());
            counter.count(count);
        }
        return counter;
    }


}
