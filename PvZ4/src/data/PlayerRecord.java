package data;

import java.io.*;
import java.util.Hashtable;

/**
 * A data type designed to store all the previous players and their corresponding game results
 *
 * Default location: "src/data/data.txt" (The data base)
 * Dependency: GameRecord.java, Player.java
 *
 * */
public class PlayerRecord {
    public Hashtable<String, Player> record;
    public final String path;

    /**
     * Initialize a PlayerRecord object, create a new File if no file exists under "path"
     * @param path location of the document that stores player data
     * @throws IOException if the method filled to create file from the given path
     * */
    public PlayerRecord(String path) throws IOException {
        this.record = new Hashtable<>();
        this.path = path;
        File file = new File(path);
        if (file.exists()) this.readRecord(path);
    }

    /**
     * Read in file under the certain path.
     *  @param path location of the document that stores player data
     *  @throws IOException if the method filled to create file from the given path
     * */
    public void readRecord(String path) throws IOException{
        try (FileReader reader = new FileReader(path);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String playerName;
            Player player;
            String line = br.readLine();
            while (line!=null ) {
                if (line.equals("-1")){
                    String name = br.readLine();
                    String pw = br.readLine();
                    if (this.record.isEmpty() || this.record.get(name)==null){
                        //if such a user does not exist
                        player = new Player(name,pw);
                        line = br.readLine();
                        while (line.equals("-99")){
                            player.addGame(br.readLine(),br.readLine(), br.readLine());
                            line = br.readLine();
                            if (line==null) break;
                        }

                        this.record.put(name,player);
                    } else {
                        //if the player already exists in the list
                        String pwOld = this.record.get(name).password;
                        if (!pw.equals(pwOld)){
                            //password does not match
                            System.out.println("Wrong Passward");
                            line = br.readLine();
                            while (line == null || (!line.equals("-1")))
                                line = br.readLine();
                            //continue;
                        }else{
                            player = this.record.get(name);
                            assert (player!=null);
                            line = br.readLine();
                            while (line.equals("-99")){
                                player.addGame(br.readLine(), br.readLine(), br.readLine());
                                line = br.readLine();
                                if (line==null) break;
                            }
                            this.record.put(name,player);
                        }
                    }
                }else{
                    //the first line is not -1
                    line = br.readLine();
                    while (line == null || (!line.equals("-1")))
                        line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write new "content" in the file under "path", create a new file if no such file exists.
     *
     *  @param path location of the document that stores player data
     *  @param content the new line to be added to the end of the existing file.
     * */
    private void write(String path, String content) {
        File file = null;
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        //BufferedWriter bw = null;
        try {
            file = new File(path);
            if(!file.exists()){
                file.createNewFile();
                fos = new FileOutputStream(file);
            }else
                fos = new FileOutputStream(file,true);
            writer = new OutputStreamWriter(fos);
            writer.write(content);
            writer.write("\r\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write in new player information, create a new file if no such file exists.
     * Write at the bottom of the existing file.
     * @param name of the player to be added
     * @param pw password of the player, used to verify player identity
     * @param win whether the play has won the match, 0 for lose and 1 for win
     * @param time duration of this game, in seconds
     * @param level difficulty level of this game, 1,2,3 respectively for easy, medium, and hard
     * */
    public void writeRecord(String name, String pw, int win,  int time, int level){
        if (level!=1 && level!=2 && level!=3)
            throw new IllegalArgumentException("illegal level");
        if (win!=1 && win!=0)
            throw new IllegalArgumentException("illegal win/lose");
        this.write(path,"\r\n");
        this.write(path,"-1");
        this.write(path,name);
        this.write(path,pw);
        this.write(path,"-99");
        this.write(path,win+"");
        this.write(path,time+"");
        this.write(path,level+"");

    }


    public static void main(String[] args) throws IOException {
        //simple unit test
        //call these methods and show they work
        PlayerRecord test = new PlayerRecord("src/data/data.txt");
        //test.writeRecord("src/data/data.txt","Test BoT","88888888", 103, 2);
        System.out.println(test.record.size());
        System.out.println(test.record.keySet());
    }

}
