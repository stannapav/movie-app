package org.example.movieapp.mappers;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.MovieDTO;
import org.example.movieapp.db.dto.UserDTO;
import org.example.movieapp.db.entities.User;
import org.example.movieapp.db.enums.WatchStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final MovieMapper movieMapper;

    public UserDTO toDTO(User user) {
        List<MovieDTO> watched = user.getUserMovies().stream()
                .filter(um -> um.getWatchStatus() == WatchStatus.WATCHED)
                .map(um -> movieMapper.toDTO(um.getMovie()))
                .toList();

        List<MovieDTO> watchLater = user.getUserMovies().stream()
                .filter(um -> um.getWatchStatus() == WatchStatus.WATCH_LATER)
                .map(um -> movieMapper.toDTO(um.getMovie()))
                .toList();

        List<MovieDTO> dropped = user.getUserMovies().stream()
                .filter(um -> um.getWatchStatus() == WatchStatus.DROPPED)
                .map(um -> movieMapper.toDTO(um.getMovie()))
                .toList();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setWatchedMovies(watched);
        userDTO.setWatchLaterMovies(watchLater);
        userDTO.setDroppedMovies(dropped);

        return userDTO;
    }
}
