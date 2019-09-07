package ui;

public class Main implements Runnable {

    GUI gui = new GUI();

    public static void main(String[] args) {

        // when run the main method, going to create new thread and start the run() method
        new Thread(new Main()).start();

    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        while(true) {

            gui.repaint();
        }

    }
}
