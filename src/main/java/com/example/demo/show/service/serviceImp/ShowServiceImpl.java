package com.example.demo.show.service.serviceImp;

import com.example.demo.show.dto.showrequestdto.ShowRequestDto;
import com.example.demo.show.dto.showresponsedto.ShowResponseDto;
import com.example.demo.show.entity.Show;
import com.example.demo.show.exception.ShowNotFoundException;
import com.example.demo.show.repository.ShowRepository;
import com.example.demo.show.service.ShowService;
import com.example.demo.theatre.entity.Screen;
import com.example.demo.theatre.repository.ScreenRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final ScreenRespository screenRespository;


    //Helper method
    private ShowResponseDto map(Show show) {
        return ShowResponseDto.builder()
                .id(show.getId())
                .movieId(show.getMovieId())
                .screenId(show.getScreen().getId())
                .startTime(show.getStartTime())
                .endTime(show.getEndTime())
                .price(show.getPrice())
                .build();
    }


    //Create Show
    @Override
    @CacheEvict(value = "shows", allEntries = true)
    public ShowResponseDto createShow(ShowRequestDto dto) {

        log.info("Creating show for movieId={}, screenId={}",
                dto.getMovieId(), dto.getScreenId());

        Screen screen = screenRespository.findById(dto.getScreenId())
                .orElseThrow(() ->
                        new RuntimeException("Screen not found"));

        Show show = new Show();
        show.setMovieId(dto.getMovieId());
        show.setStartTime(dto.getStartTime());
        show.setEndTime(dto.getEndTime());
        show.setPrice(dto.getPrice());
        show.setScreen(screen);
        Show saved = showRepository.save(show);
        log.info("Show created with id={}", saved.getId());

        return map(saved);

    }

    //Get show by id
    @Override
    public ShowResponseDto getShowById(Long showId) {

        log.debug("Fetching show by id={}", showId);
        Show show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException(showId));
        return map(show);
    }

    //Get All shows
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "shows", key = "'all_' + #page + '_' + #size")
    public List<ShowResponseDto> getAllShows(int page, int size) {

        log.debug("Fetching all shows page={}, size={}", page, size);
        /*return showRepository.findAll(PageRequest.of(page, size))
                .map(this::map);*/
        return showRepository.findAll(PageRequest.of(page, size))
                .map(this::map)    // Page<ShowResponseDto>
                .getContent();
    }


    //Get shows by movie
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "shows", key = "'movie_' + #movieId + '_' + #page")
    public Page<ShowResponseDto> getShowsByMovie(String movieId, int page, int size) {

        log.debug("Fetching shows by movieId={}", movieId);
        return showRepository.findByMovieId(movieId, PageRequest.of(page, size))
                .map(this::map);
    }


    //Get shows by screen
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "shows", key = "'screen_' + #screenId + '_' + #page")
    public Page<ShowResponseDto> getShowsByScreen(Long screenId, int page, int size) {


        log.debug("Fetching shows by screenId={}", screenId);
        return showRepository.findByScreen_Id(screenId, PageRequest.of(page, size))
                .map(this::map);

    }


    //Update Show by id
    @Override
    @CacheEvict(value = "shows", allEntries = true)
    public ShowResponseDto updateShow(Long showId, ShowRequestDto dto) {
        log.info("Updating show id={}", showId);

        Show show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException(showId));
        Screen screen = screenRespository.findById(dto.getScreenId())
                .orElseThrow(() ->
                        new RuntimeException("Screen not found"));

        show.setMovieId(dto.getMovieId());
        show.setStartTime(dto.getStartTime());
        show.setEndTime(dto.getEndTime());
        show.setPrice(dto.getPrice());
        show.setScreen(screen);

        Show updated = showRepository.save(show);
        log.info("Show updated id={}", updated.getId());
        return map(updated);
    }


    //Delete show
    @Override
    @CacheEvict(value = "shows", allEntries = true)
    public void deleteShow(Long showId) {

        log.warn("Deleting show id={}", showId);
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException(showId));
        showRepository.delete(show);
    }
}
