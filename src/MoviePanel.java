import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoviePanel extends JPanel {
    JList<String> movieList;
    DefaultListModel<String> listModel;
    JButton selectMovieButton, backButton;
    MainFrame parentFrame;

    public MoviePanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Now Showing Movies", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Movie List Model & JList (very standard for 2nd-year Java Swing)
        listModel = new DefaultListModel<>();
        listModel.addElement("Inception (Sci-Fi) - 6:00 PM");
        listModel.addElement("Interstellar (Adventure) - 8:30 PM");
        listModel.addElement("The Dark Knight (Action) - 9:00 PM");
        listModel.addElement("Avengers: Endgame (Action) - 4:00 PM");

        movieList = new JList<>(listModel);
        movieList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(movieList);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        selectMovieButton = new JButton("Select Movie");
        backButton = new JButton("Back to Dashboard");
        
        btnPanel.add(selectMovieButton);
        btnPanel.add(backButton);
        add(btnPanel, BorderLayout.SOUTH);

        // Action Listeners (traditional style, easy to explain)
       selectMovieButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedMovie = movieList.getSelectedValue();
        if (selectedMovie == null) {
            JOptionPane.showMessageDialog(null, "Please select a movie first!");
        } else {
            // Switch to Theatre selection screen (Day 5 task)
            parentFrame.showTheatres();
        }
    }
});

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("HOME");
            }
        });
    }
}