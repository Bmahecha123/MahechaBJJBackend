package com.example.mahechabjj.mahechabjj.Controller;


import com.example.mahechabjj.mahechabjj.Model.PlayList;
import com.example.mahechabjj.mahechabjj.Model.User;
import com.example.mahechabjj.mahechabjj.Model.Video;
import com.example.mahechabjj.mahechabjj.Repository.UserRepository;
import com.example.mahechabjj.mahechabjj.Service.EncryptionServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @GetMapping("user/getUser")
    public User getUserByEmail(@RequestHeader("X-EMAIL") String email) {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
           return null;
        } else {
            return user;
        }
    }

    @DeleteMapping("user")
    public boolean deleteUser(@RequestHeader("X-ID") String id) {

        User user = this.userRepository.findOne(id);
        if (user != null) {
            this.userRepository.delete(user.getId());
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("password/changePassword")
    public void changePassword(@RequestHeader("X-ID") String id, @RequestHeader("X-ANSWER") String answer, @RequestHeader("X-PASSWORD") String password) {
        User user = userRepository.findUserById(id);
        String secretQuestion = user.getSecretQuestion();

        if (Objects.equals(answer, user.getSecretQuestionAnswer())) {
            user.setPassword(password);
            String hashedPassword = hashPassword(user);
            user.setPassword(hashedPassword);
            userRepository.save(user);
        }
    }

    @GetMapping("user/findById")
    public User findUserById(@RequestHeader("X-Id") String id) {
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userExist = userRepository.findUserByEmail(user.getEmail());
        if (userExist != null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String hashedPassword = hashPassword(user);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PutMapping("edit")
    public ResponseEntity editUser(@RequestBody User user) {
        String hashedPassword = hashPassword(user);
        userRepository.save(user);

        return new ResponseEntity(HttpStatus.OK);
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

    public String hashPassword(User user) {

        String hashedPassword = encryptionService.encryptString(user.getPassword());
        return hashedPassword;
    }
}
