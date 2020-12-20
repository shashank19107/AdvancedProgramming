import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class NewApp extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton submitButton;
    private JCheckBox towerACheckBox;
    private JCheckBox towerBCheckBox;
    private JCheckBox towerCCheckBox;
    private JCheckBox towerDCheckBox;
    private JTextField textField3;
    private JButton showListOfPeopleButton;
    private JTextArea textArea1;
    private JTextField textField4;
    private static ArrayList<patient> patients = new ArrayList<patient>();

    public NewApp() {
        // panel1.size(500,500);
        // JFrame frame = new JFrame("NewApp");
        // frame.size();
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // NewApp alph = new NewApp();
        pack();
        setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String get = textField1.getText();
                if (get.length() < 10) {
                    textArea1.setText("\t\tEnter a Valid Date.");
                    return;
                }
                int day = Integer.parseInt(get.substring(0, 2));
                int mon = Integer.parseInt(get.substring(3, 5)) - 1;
                int yy = Integer.parseInt(get.substring(6));
                if (day > 31 || mon > 12) {
                    textField2.setText("Invalid Date Input");
                    return;
                }
                Calendar calendar = Calendar.getInstance();
                // calendar.setTime(startDate);
                calendar.set(Calendar.MONTH, mon);
                calendar.set(Calendar.DATE, day);
                calendar.set(Calendar.YEAR, yy);
                //startDate
                Date currDate = (Date) calendar.getTime();
                boolean[] towers = new boolean[4];
                if (towerACheckBox.isSelected()) {
                    towers[0] = true;
                }
                if (towerBCheckBox.isSelected()) {
                    towers[1] = true;
                }
                if (towerCCheckBox.isSelected()) {
                    towers[2] = true;
                }
                if (towerDCheckBox.isSelected()) {
                    towers[3] = true;
                }
                int Infected = 0, recovered = 0;
                textArea1.setText("");
                for (int i = 0; i < patients.size(); i++) {
                    patient temp = patients.get(i);
                    if (towers[temp.tower - 'A'] && temp.startDate.compareTo(currDate) <= 0 && temp.EndDate.compareTo(currDate) <= 0) {
                        recovered++;
                        textArea1.append(temp.name + " (" + temp.age + ") \t(Tower: " + temp.tower + ") \t Recovered on: " + temp.ed + "/" + temp.em + "/20" + temp.ey + "\n");

                    } else if (towers[temp.tower - 'A'] && temp.startDate.compareTo(currDate) <= 0) {
                        Infected++;
                        textArea1.append(temp.name + " (" + temp.age + ") \t(Tower: " + temp.tower + ") \t Will Recover on: " + temp.ed + "/" + temp.em + "/20" + temp.ey + "\n");
                    }
                }
                textField2.setText(recovered + " ");
                textField3.setText(Infected + " ");
                if (textArea1.getText().equals("")) {
                    textArea1.setText("List Empty!!");
                }


            }
        });
        showListOfPeopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(showListOfPeopleButton, textArea1.getText());
            }
        });
    }

    public static void main(String srgs[]) {
        patients.add(new patient("Flora", 6, 'A', "01/04/2020"));
        patients.add(new patient("Denys", 24, 'B', "01/04/2020"));
        patients.add(new patient("Jim", 42, 'C', "18/05/2020"));
        patients.add(new patient("Hazel", 87, 'D', "23/06/2020"));
        patients.add(new patient("Caery", 72, 'A', "01/06/2020"));
        patients.add(new patient("David", 7, 'B', "14/06/2020"));
        patients.add(new patient("Kevim", 37, 'D', "05/06/2020"));
        patients.add(new patient("Tom", 67, 'D', "20/06/2020"));
        patients.add(new patient("Bob", 74, 'A', "04/07/2020"));
        patients.add(new patient("Rachel", 48, 'C', "24/07/2020"));
        patients.add(new patient("Thomas", 21, 'C', "11/06/2020"));
        patients.add(new patient("Mary", 17, 'D', "21/06/2020"));
        patients.add(new patient("Smith", 89, 'A', "07/08/2020"));
        patients.add(new patient("Pearson", 47, 'B', "04/06/2020"));
        patients.add(new patient("Anderson", 62, 'B', "27/07/2020"));
        patients.add(new patient("Johnson", 10, 'D', "01/08/2020"));
        patients.add(new patient("Robertz", 50, 'A', "09/08/2020"));
        patients.add(new patient("Julie", 86, 'B', "02/05/2020"));
        patients.add(new patient("Edith", 42, 'D', "07/06/2020"));
        patients.add(new patient("John", 95, 'D', "01/06/2020"));
        for (int i = 0; i < patients.size(); i++) {
            for (int j = 0; j < patients.size() - 1; j++) {
                if (patients.get(j).startDate.compareTo(patients.get(j + 1).startDate) < 0) {
                    patient temp = patients.get(j);
                    patients.remove(j);
                    patients.add(j + 1, temp);
                }
            }
        }

        NewApp aa = new NewApp();


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(15, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setPreferredSize(new Dimension(500, 500));
        final JLabel label1 = new JLabel();
        label1.setText("Advanced Programming Assignment");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setText("");
        panel1.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField2 = new JTextField();
        panel1.add(textField2, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        submitButton = new JButton();
        submitButton.setText("Submit");
        panel1.add(submitButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Enter the Date in Format (DD/MM/YYYY)");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Select the Towers (select at least 1 tower)");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        towerACheckBox = new JCheckBox();
        towerACheckBox.setText("Tower A");
        panel1.add(towerACheckBox, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        towerBCheckBox = new JCheckBox();
        towerBCheckBox.setText("Tower B");
        panel1.add(towerBCheckBox, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        towerCCheckBox = new JCheckBox();
        towerCCheckBox.setText("Tower C");
        panel1.add(towerCCheckBox, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        towerDCheckBox = new JCheckBox();
        towerDCheckBox.setText("Tower D");
        panel1.add(towerDCheckBox, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Recovered Patients");
        panel1.add(label4, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Currently Infected People");
        panel1.add(label5, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        panel1.add(textField3, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        showListOfPeopleButton = new JButton();
        showListOfPeopleButton.setText("Show List of People");
        panel1.add(showListOfPeopleButton, new com.intellij.uiDesigner.core.GridConstraints(13, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textArea1 = new JTextArea();
        textArea1.setText("");
        panel1.add(textArea1, new com.intellij.uiDesigner.core.GridConstraints(14, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}


class patient{
    //static HashMap<char,patient> map = new HashMap<char,patient>();
    String name;
    int age;
    char tower;
    Date startDate, EndDate;
    int ed,em,ey;
    patient(String name, int age, char tower, String date){
        this.name = name;
        this.age = age;
        this.tower = tower;
        // System.out.println(Integer.parseInt(date.substring(6)));
        // System.out.println( Integer.parseInt(date.substring(3,5)));
        // System.out.println(Integer.parseInt(date.substring(0,2)));
        //startDate = new Date(Integer.parseInt(date.substring(6)), Integer.parseInt(date.substring(3,5)), Integer.parseInt(date.substring(0,2)));
        Calendar calendar = Calendar.getInstance();
        // calendar.setTime(startDate);
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(3,5))-1);
        calendar.set(Calendar.DATE, Integer.parseInt(date.substring(0,2)) );
        calendar.set(Calendar.YEAR,Integer.parseInt(date.substring(6)));
        startDate = (Date) calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 21);

        EndDate = (Date) calendar.getTime();
        em= EndDate.getMonth()+1;
        ed = EndDate.getDate();
        ey = EndDate.getYear()%100;


        // System.out.println(startDate);
        // System.out.println(EndDate);
    }


}