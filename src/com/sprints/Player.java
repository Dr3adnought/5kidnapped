package com.sprints;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.*;
import static com.sprints.OurJSONParser.*;

public class Player {
    // ******** Class Singleton **********
    private static Player player = null;

    // ******** Fields **********
    private String currentRoom = "basement";
    private Map<String, String> inventory = new HashMap<>();
    private boolean itemEquipped = false;
    private final List<String> locations = Arrays.asList("kitchen", "parlor", "east room", "west hall");
    private String guiConnection;

    // separate synonyms.json at indexes
    JSONArray verbObj1 = (JSONArray) OurJSONParser.getSynJSON().get(0); // go
    JSONArray verbObj2 = (JSONArray) OurJSONParser.getSynJSON().get(1); // get
    JSONArray verbObj3 = (JSONArray) OurJSONParser.getSynJSON().get(2); // look
    JSONArray verbObj4 = (JSONArray) OurJSONParser.getSynJSON().get(3); // equip
    JSONArray verbObj5 = (JSONArray) OurJSONParser.getSynJSON().get(4); // drop
    JSONArray verbObj6 = (JSONArray) OurJSONParser.getSynJSON().get(5); // raise
    JSONArray verbObj7 = (JSONArray) OurJSONParser.getSynJSON().get(6); // lower

    private Player () {
    }
    // ******** Business Methods **********
    /* we do not want to instantiate multiple.
    static allows us to use through entire app where needed.*/
    public static Player getInstance() {
        if (player == null || Restart.getRestart()) {
            player = new Player();
        }
        return player;
    }

    // ******** Business Methods **********
    /* takes in all commands along with current room info, rooms.json info,
    synonyms, and valid items */
    public void playerActions(List<String> commands)
            throws LineUnavailableException, UnsupportedAudioFileException, IOException {



        // pass to function depending on which synonym array verb belongs to
        if (verbObj1.contains(commands.get(0))) {
            locationChange(commands.get(1));                                            //go logic
        }else if (verbObj2.contains(commands.get(0))) {
            getItems(commands.get(1));          //get logic
        }else if (verbObj3.contains(commands.get(0))) {
            look(commands.get(1));                    //look logic
        }else if (verbObj4.contains(commands.get(0))) {
            equip(commands.get(1));                                                                     //equip logic
        }else if (verbObj5.contains(commands.get(0))) {
            dropItems(commands.get(1));                                          //drop logic
        }else if (verbObj6.contains(commands.get(0))) {
            MusicPlayer.raiseSoundVolume();                                                             //raise sound
        }else if (verbObj7.contains(commands.get(0))) {
            MusicPlayer.lowerSoundVolume();                                                             //lower sound
        }
    }

    // pick up items
    void getItems(String noun) {
        // check if item is a valid and something we can hold in inventory

        if(roomItems!=null && OurJSONParser.getItems().contains(noun) && getInventoryJSON().containsKey(noun)) {
            //check if the room had the item player is trying to get
            String itemDescription ="";
            if (roomItems.containsKey(noun)) {
//                inventory.add(noun);
                if (roomItems.get(noun) instanceof String){
                    itemDescription = roomItems.get(noun).toString();
                }
                else {
                    JSONObject item = (JSONObject) roomItems.get(noun);
                    itemDescription = item.get("description").toString();
                }
                inventory.put(noun,itemDescription);
                roomItems.remove(noun);
                System.out.println(noun + " picked up");
                setGuiConnection(noun + " picked up");

            }
            // check location and if noun is the title of one of the books on bookcase
            else if (getCurrentRoom().equals("west hall") && OurJSONParser.getBooks().containsKey(noun)) {
//                inventory.add(noun);

                JSONObject description = (JSONObject) OurJSONParser.getBooks().get(noun);

                inventory.put(noun, description.get("description").toString());
                OurJSONParser.getBooks().remove(noun);
                System.out.println(noun + " picked up");

                setGuiConnection(noun + " picked up");
            }
            else if (!roomItems.containsKey(noun) && inventory.containsKey(noun)) {
                System.out.println("You already have " + noun);
                setGuiConnection("You already have " + noun);
            }


            // what to do if noun is not present clearly present in room

            //"kitchen".equals(getCurrentRoom()) || "parlor".equals(getCurrentRoom()) || "east room".equals(getCurrentRoom()) || "west hall".equals(getCurrentRoom())
            else if (!roomItems.containsKey(noun) && locations.contains(getCurrentRoom())) {
                // switch case for rooms that hold clues inside other objects
                switch (getCurrentRoom()) {
                    case "kitchen":
                        OurJSONParser.setClueHolder((JSONObject) roomItems.get("cabinets"));
                        break;
                    case "east room":
                        OurJSONParser.setClueHolder((JSONObject) roomItems.get("vase"));
                        break;
                    case "parlor":
                        OurJSONParser.setClueHolder((JSONObject) roomItems.get("portrait"));
                        break;
                    case "west hall":
                        OurJSONParser.setClueHolder((JSONObject) roomItems.get("bookcase"));
                        break;
                    default:
                        break;
                }

                JSONObject clueObj = (JSONObject) OurJSONParser.getClueHolder().get("clue");

                // if user picks clue up and drops in another room
                // if they go back to orig location an 'get clue' again clueObj is null
                if(clueObj==null) {
                    // but needs to say cant get noun
                    System.out.println(noun + " is not in this room");
                    setGuiConnection(noun + " is not in this room");
                    return;
                }

                String clue = (String) clueObj.get("name");

                // check is noun is equal to name of one of our clues
                if(noun.equals(clue)) {
//                    inventory.add(clue);
                    inventory.put(clue, clueObj.get("description").toString());
                    OurJSONParser.getClueHolder().remove("clue");
                    clueHolder.replace("description", clueHolder.get("description2"));
                    System.out.println(noun + " picked up");
                    setGuiConnection(noun + " picked up");
                }
                else {
                    System.out.println(noun + " is not in this room");
                    setGuiConnection(noun + " is not in this room");
                }
            }


        }
        // how we handle it if item is not valid or something player cannot hold in inventory
        else {
            System.out.println("You cannot pick up " + noun);
            //plug = "You cannot pick up " + noun;
            setGuiConnection("You cannot pick up " + noun);
        }
    }



    // allows player to drop items
    private void dropItems(String noun) {

        if (OurJSONParser.getItems().contains(noun) && !roomItems.containsKey(noun)) {
            System.out.println(noun + " dropped");
            roomItems.put(noun, inventory.get(noun));
            inventory.remove(noun);
        }
        if (noun.equals("torch")) {
            itemEquipped = false;
        }

    }

    // allow player to equip items (only torch at this moment)
    private void equip(String noun) {
        if (!"torch".equals(noun)){
            System.out.println("You can only equip a torch at this time.");
        }
        else if (!inventory.containsKey("torch")) {
            System.out.println("You must get the torch to use it!");
        }
        else {
            System.out.println("Torch equipped");
            itemEquipped = true;
        }
    }

    // change player location
    private void locationChange(String noun) {
        // if the roomsObj (json file with room info) has a location with a name that matches the player
        // input noun, we set it as the current room
        if(OurJSONParser.getRoomsJSON().containsKey(noun)) {
            setCurrentRoom(noun);
        }
        // checks if current room has a given direction (north, south, east, west) amd if so
        //changes current room to the value of said direction
        else if (OurJSONParser.getRoom().containsKey(noun)) {
            setCurrentRoom((String) OurJSONParser.getRoom().get(noun));

        }
        else {
            System.out.println("You cannot go that way");
        }
    }

    // look at room and items in room
    private void look(String noun) {
        Set<String> bookKeys = OurJSONParser.getBooks().keySet();

        // checks location as west hall the only place books keyword is valid and
        // outputs the title of the books on the bookcase for player
        if ("west hall".equals(getCurrentRoom()) && "books".equals(noun) && itemEquipped) {
            System.out.println("You see " + bookKeys);
            setGuiConnection("You see " + bookKeys);
        }
        else if ("west hall".equals(getCurrentRoom()) && bookKeys.contains(noun) && itemEquipped) {
            JSONObject book = (JSONObject) OurJSONParser.getBooks().get(noun);
            String description = (String) book.get("description");
            System.out.println(description);
            setGuiConnection(description);
        }
        // gives description if so prints out that room description
        else if (noun.equals(getCurrentRoom()) || "here".equals(noun) && itemEquipped) {
            System.out.println(room.get("description"));

        }

        // gives description of items if they are in player inventory
        else if (inventory.containsKey(noun) && itemEquipped) { //inventory.contains(noun) && itemEquipped
            System.out.println(inventory.get(noun).toString());
            setGuiConnection(inventory.get(noun.toString()));
        }

        // allows player to get description of items in room
        else if (OurJSONParser.getItems().contains(noun) && room.containsKey("item") && roomItems.containsKey(noun) && itemEquipped){
            JSONObject item;
            if (roomItems.get(noun) instanceof java.lang.String) {
                System.out.println(roomItems.get(noun).toString());
                setGuiConnection(roomItems.get(noun).toString());
            }
            else {
                item = (JSONObject) roomItems.get(noun);
                System.out.println(item.get("description"));
                setGuiConnection((String) item.get("description"));
            }
////            System.out.println(item.get("description"));
//            else {
//            //System.out.println(roomItems.get(noun).toString());
//            }
        }
        // response if player attempts to
        else if (!noun.contains(getCurrentRoom()) && itemEquipped || !roomItems.containsKey(noun) && itemEquipped) {
            System.out.println("You cannot see " + noun + " from here");
           // plug = "You cannot see " + noun + " from here";
            return;
        }
        else {
            System.out.println("Too dark to see. Some light would help");
            //plug = "Too dark to see. Some light would help";
            return;
        }
        //Utils.pressEnterToContinue();
        //setIsLook(true);
    }

    public List<String> getCurrentInventory(){
        return new ArrayList<String>(Player.getInstance().getInventory().keySet());
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Map<String, String> getInventory() {
        return inventory;
    }


    public boolean isItemEquipped() {
        return itemEquipped;
    }
    public void setItemEquipped(boolean itemEquipped) {
        this.itemEquipped = itemEquipped;
    }


    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Player.player = player;
    }

    public String getGuiConnection() {
        return guiConnection;
    }

    public void setGuiConnection(String guiConnection) {
        this.guiConnection = guiConnection;
    }
}