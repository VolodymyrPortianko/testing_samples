package example100.filmlibrary.service.impl;

import com.google.common.collect.Lists;
import example100.filmlibrary.dao.FilmDao;
import example100.filmlibrary.entity.Film;
import example100.filmlibrary.service.FilmService;
import example100.filmlibrary.util.exeption.NotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created on 22.11.2016.
 * Time 21:46.
 *
 * @author Volodymyr Portianko
 */
@RunWith(MockitoJUnitRunner.class)
public class FilmServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Spy
    @InjectMocks
    private FilmService sut = new FilmServiceImpl();

    @Mock
    private FilmDao filmDao;

    @Mock
    private Film dummyFilm;

    @Test
    public void testSave() throws Exception {
        when(filmDao.save(dummyFilm)).thenReturn(dummyFilm);

        Film actualFilm = sut.save(dummyFilm);

        verify(filmDao).save(dummyFilm);
        assertSame(dummyFilm, actualFilm);
    }

    @Test
    public void testUpdate() throws Exception {
        int anyId = 1;
        when(dummyFilm.getId()).thenReturn(anyId);
        when(filmDao.get(anyId)).thenReturn(dummyFilm);

        sut.update(dummyFilm);

        verify(filmDao).save(dummyFilm);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        int anyId = 1000;
        when(dummyFilm.getId()).thenReturn(anyId);
        when(filmDao.get(anyId)).thenReturn(null);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage(String.format("Film with id=%d is not found", anyId));

        sut.update(dummyFilm);
    }

    @Test
    public void testGet() throws Exception {
        int anyId = 1;
        when(filmDao.get(anyId)).thenReturn(dummyFilm);

        Film actual = sut.get(anyId);

        assertSame(dummyFilm, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        int anyId = 1000;
        when(filmDao.get(anyId)).thenReturn(null);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage(String.format("Film with id=%d is not found", anyId));

        Film actual = sut.get(anyId);
    }

    @Test
    public void testGetByName() throws Exception {
        String anyName = "anyName";
        when(filmDao.getByName(anyName)).thenReturn(dummyFilm);

        Film actual = sut.getByName(anyName);

        assertSame(dummyFilm, actual);
    }

    @Test
    public void testGetByNameNotFound() throws Exception {
        String anyNonExistingName = "anyNonExistingName";
        when(filmDao.getByName(anyNonExistingName)).thenReturn(null);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage(String.format("Film with name=%s is not found", anyNonExistingName));

        sut.getByName(anyNonExistingName);
    }

    @Test
    public void testDelete() throws Exception {
        int anyId = 1;
        when(filmDao.delete(anyId)).thenReturn(true);

        sut.delete(anyId);

        verify(filmDao).delete(anyId);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        int anyId = 100;
        when(filmDao.delete(anyId)).thenReturn(false);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage(String.format("Film with id=%d is not found", anyId));

        sut.delete(anyId);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Film> expected = Lists.newArrayList(dummyFilm, dummyFilm, dummyFilm);
        when(filmDao.getAll()).thenReturn(expected);

        List<Film> actual = filmDao.getAll();

        assertEquals(expected, actual);
    }

}