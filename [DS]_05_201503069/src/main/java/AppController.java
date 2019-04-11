public class AppController {
    private Experiment _experiment;


    public Experiment experiment() {
        return _experiment;
    }

    public void setExperiment(Experiment newExperiment) {
        this._experiment = newExperiment;
    }

    public AppController() {
        this.setExperiment(new Experiment());
        this.experiment().generateData();
    }

    public void run() {

        AppView.outputLine("<<< ����Ʈ ���� ���� ���α׷��� �����մϴ�.  >>> ");
        AppView.outputLine("! ����Ʈ ������ ���� ���̸� �˾ƺ��ϴ�. (����: Micro Second)");


        AppView.outputLine("");
        AppView.outputLine("<UnSorted Array List >");
        this.experiment().measureForUnSortedArrayList();
        this.showExperimentResults();


        AppView.outputLine("");
        AppView.outputLine("<Sorted Array List >");
        this.experiment().measureForSortedArrayList(); //���� ���� ����
        this.showExperimentResults(); //����� ȭ�鿡 ����Ѵ�. �����ѰŴ� controller ����

        //Linked Unsorted
        AppView.outputLine("");
        AppView.outputLine("<UnSorted Linekd List >");
        this.experiment().measureForUnSortedLinkedList();
        this.showExperimentResults();


        //Linked sorted
        AppView.outputLine("");
        AppView.outputLine("<Sorted Linekd List >");
        this.experiment().measureForSortedLinkedList();
        this.showExperimentResults();

        AppView.outputLine("<<< ����Ʈ ���� ���� ���α׷��� ���� �մϴ�, >>>");


    }

    private void showExperimentResults() {
        MeasuredResult[] results = this.experiment().measuredResults();
        for (int i = 0; i < this.experiment().numberOfIteration(); i++) {
            AppView.outpuResults(
                    results[i].size(),
                    results[i].durationForAdd() / 1000,
                    results[i].durationForMax() / 1000
            );
        }

    }
}

