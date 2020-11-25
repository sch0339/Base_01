package tech.thdev.java_udemy_sample.view.image.presenter;

import org.awaitility.core.ThrowingRunnable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import tech.thdev.java_udemy_sample.data.source.image.ImageRepository;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by tae-hwan on 11/13/16.
 */
public class PhotoViewPresenterImplTest {

    private PhotoViewPresenter.View view;
    private PhotoViewPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(PhotoViewPresenter.View.class);
        presenter = new PhotoViewPresenterImpl(view, ImageRepository.getInstance());
    }

    @Test
    public void recentPhotoData() throws Exception {
        final boolean[] finish = {false};
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                finish[0] = true;
                System.out.println("----finish " + finish[0]);
                return invocationOnMock;
            }
        }).when(view).showLoaded();

        presenter.recentPhotoData();
        await().untilAsserted(new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                while (!finish[0]) {
                    // ...
                    System.out.println();
                }
            }
        });

        verify(view).showLoaded();
    }
}