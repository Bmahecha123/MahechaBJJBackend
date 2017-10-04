package com.example.mahechabjj.mahechabjj.Controller;


import com.example.mahechabjj.mahechabjj.Model.PlayList;
import com.example.mahechabjj.mahechabjj.Model.User;
import com.example.mahechabjj.mahechabjj.Model.Video;
import com.example.mahechabjj.mahechabjj.Repository.UserRepository;
import com.example.mahechabjj.mahechabjj.Service.EncryptionServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserRepository userRepository;
    private EncryptionServiceImpl encryptionService;

    @Autowired
    public UserController( UserRepository userRepository, EncryptionServiceImpl encryptionService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
    }

    @GetMapping("user/test")
    public String test(){
        return "yo bitch";
    }

    @GetMapping("user/findById/{id}")
    public User findUserById(@PathVariable("id") String id) {
        return userRepository.findUserById(id);
    }

    @GetMapping("user/findByEmail")
    public ResponseEntity<User> findUserByEmail(@RequestHeader("X-Email") String email, @RequestHeader("X-Password") String password) {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        //String hashedPassword = encryptionService.encryptString(user.getPassword());
        boolean passwordMatch = encryptionService.checkPassword(password, user.getPassword());
        if (passwordMatch) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("user/create")
    public User createUser(@RequestBody User user) {
        String hashedPassword = encryptionService.encryptString(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        userRepository.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("edit/{id}")
    public ResponseEntity  editUser(@RequestBody User user, @PathVariable("id") String id) {
        //TODO implement the editing functionality.
        //perhaps with a custom repository function to perform more of the logic and etc.
        return null;
    }

    @PutMapping("user/addplaylist/{id}")
    public ResponseEntity addPlaylist(@PathVariable("id") String id, @RequestBody PlayList playList) {
        User user = userRepository.findOne(id);
        ArrayList<PlayList> playListList = user.getPlaylists();
        playListList.add(playList);
        user.setPlaylists(playListList);

        userRepository.save(user);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("user/getplaylists/{id}")
    public ResponseEntity<List<PlayList>> getPlaylists(@PathVariable("id") String id){
        User user = userRepository.findOne(id);
        List<PlayList> playListList = user.getPlaylists();

        return new ResponseEntity<List<PlayList>>(playListList, HttpStatus.OK);
    }

    @GetMapping("user/getplaylist/{id}")
    public ResponseEntity<PlayList> getPlaylist(HttpServletRequest headers, @PathVariable("id") String id){

        String playListName = headers.getHeader("X-playlistName");
        User user = userRepository.findOne(id);
        List<PlayList> playListList = user.getPlaylists();

        PlayList userPlaylist = null;

        for (PlayList playlist : playListList){
            if (playlist.getName().contains(playListName)){
                userPlaylist = playlist;
            }
        }

        return new ResponseEntity<PlayList>(userPlaylist, HttpStatus.OK);
    }

    @PostMapping("user/updateplaylists/{id}")
    public ResponseEntity updateUserPlaylist(@PathVariable("id") String id,
        @RequestBody PlayList newPlayList) {

        User user = userRepository.findOne(id);
        ArrayList<PlayList> playListList = user.getPlaylists();
        PlayList playListToBeRemoved = new PlayList();

        for (PlayList playlist : playListList){
            if (playlist.getName().contains(newPlayList.getName())){
                playListToBeRemoved = playlist;
            }
        }
        playListList.remove(playListToBeRemoved);
        playListList.add(newPlayList);
        user.setPlaylists(playListList);

        userRepository.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("user/deleteplaylist/{id}")
    public ResponseEntity deleteUserPlaylist(@PathVariable("id") String id,
        @RequestBody PlayList playlistToBeDeleted) {

        User user = userRepository.findOne(id);
        ArrayList<PlayList> playListList = user.getPlaylists();

        for (PlayList playlist : playListList) {
            if (playlist.getName().contains(playlistToBeDeleted.getName())){
                playlistToBeDeleted = playlist;
            }
        }
        playListList.remove(playlistToBeDeleted);
        user.setPlaylists(playListList);
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("user/deleteVideo/{id}")
    public ResponseEntity deleteVideoFromPlaylist(HttpServletRequest headers, @PathVariable("id") String id,
        @RequestBody PlayList userPlayList) {

        String videoName = headers.getHeader("X-videoName");

        User user = userRepository.findOne(id);
        ArrayList<PlayList> playListList = user.getPlaylists();

        for (PlayList playlist : playListList) {
            if (playlist.getName().contains(userPlayList.getName())){
                userPlayList = playlist;
            }
        }
        playListList.remove(userPlayList);
        Video videoToBeDeleted = null;

        for (Video video : userPlayList.getVideos()){
            if (video.getName().contains(videoName)){
                videoToBeDeleted = video;
            }
        }

        userPlayList.getVideos().remove(videoToBeDeleted);
        playListList.add(userPlayList);
        user.setPlaylists(playListList);

        userRepository.save(user);

        return new ResponseEntity(HttpStatus.OK);
    }
}
