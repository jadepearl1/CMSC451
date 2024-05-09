package Project1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.ToLongFunction;

public class Report extends JFrame{
    
    public Report() {
        super("Sorting Algorithm Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use JFileChooser to select the input file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            try {
                List<ReportData> reportDataList = readDataFromFile(inputFile);
                JTable reportTable = createReportTable(reportDataList);

                JScrollPane scrollPane = new JScrollPane(reportTable);
                getContentPane().add(scrollPane, BorderLayout.CENTER);
                setSize(600, 400);
                setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading data from file.");
            }
        }
    }

    private List<ReportData> readDataFromFile(File file) throws IOException {
        List<ReportData> reportDataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                int dataSetSize = Integer.parseInt(tokenizer.nextToken());
                List<RunData> runDataList = new ArrayList<>();
                for (int i = 0; i < 40; i++) {
                    int criticalCount = Integer.parseInt(tokenizer.nextToken());
                    long timeInNanoseconds = Long.parseLong(tokenizer.nextToken());
                    runDataList.add(new RunData(criticalCount, timeInNanoseconds));
                }
                reportDataList.add(new ReportData(dataSetSize, runDataList));
            }
        }
        return reportDataList;
    }

    private JTable createReportTable(List<ReportData> reportDataList) {
        Object[][] data = new Object[reportDataList.size()][5];
        String[] columnNames = {"Size", "Avg Count", "Coef Count", "Avg Time", "Coef Time"};

        for (int i = 0; i < reportDataList.size(); i++) {
            ReportData reportData = reportDataList.get(i);
            data[i][0] = reportData.getDataSetSize();
            data[i][1] = calculateAverage(reportData.getRunDataList(), RunData::getCriticalCount);
            data[i][2] = calculateCoefficientOfVariance(reportData.getRunDataList(), RunData::getCriticalCount) + "%";
            data[i][3] = calculateAverage(reportData.getRunDataList(), RunData::getTimeInNanoseconds);
            data[i][4] = calculateCoefficientOfVariance(reportData.getRunDataList(), RunData::getTimeInNanoseconds) + "%";
        }

        return new JTable(new DefaultTableModel(data, columnNames));
    }

    private double calculateAverage(List<RunData> runDataList, ToLongFunction<RunData> mapper) {
        return runDataList.stream().mapToLong(mapper).average().orElse(0);
    }

    private double calculateCoefficientOfVariance(List<RunData> runDataList, ToLongFunction<RunData> mapper) {
        double average = calculateAverage(runDataList, mapper);
        double variance = runDataList.stream().mapToDouble(data -> Math.pow(mapper.applyAsLong(data) - average, 2)).average().orElse(0);
        double stdDeviation = Math.sqrt(variance);
        double coefficientOfVariance = (stdDeviation / average) * 100;
        return Math.round(coefficientOfVariance * 100.0) / 100.0; // Round to two decimal places
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Report());
    }

    // Define your data classes
    static class ReportData {
        private int dataSetSize;
        private List<RunData> runDataList;

        public ReportData(int dataSetSize, List<RunData> runDataList) {
            this.dataSetSize = dataSetSize;
            this.runDataList = runDataList;
        }

        public int getDataSetSize() {
            return dataSetSize;
        }

        public List<RunData> getRunDataList() {
            return runDataList;
        }
    }

    static class RunData {
        private int criticalCount;
        private long timeInNanoseconds;

        public RunData(int criticalCount, long timeInNanoseconds) {
            this.criticalCount = criticalCount;
            this.timeInNanoseconds = timeInNanoseconds;
        }

        public int getCriticalCount() {
            return criticalCount;
        }

        public long getTimeInNanoseconds() {
            return timeInNanoseconds;
        }
    }

}