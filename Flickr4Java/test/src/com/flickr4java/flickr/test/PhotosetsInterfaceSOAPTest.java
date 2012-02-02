package com.flickr4java.flickr.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.SOAP;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoContext;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

/**
 * @author Anthony Eden
 */
public class PhotosetsInterfaceSOAPTest {

    Flickr flickr = null;
    private TestProperties testProperties;

    @Before
    public void setUp() throws ParserConfigurationException, IOException {
        testProperties = new TestProperties();

        Flickr.debugStream = true;
        SOAP soap = new SOAP(testProperties.getHost());
        flickr = new Flickr(testProperties.getApiKey(), testProperties.getSecret(), soap);

    }

    @Ignore
    @Test
    public void testCreateAndDelete() throws FlickrException, IOException, SAXException {
        RequestContext requestContext = RequestContext.getRequestContext();
        PhotosetsInterface iface = flickr.getPhotosetsInterface();
        Photoset photoset = iface.create("test", "A test photoset", testProperties.getPhotoId());
        assertNotNull(photoset);
        assertNotNull(photoset.getId());
        assertNotNull(photoset.getUrl());
        iface.delete(photoset.getId());
    }

    @Ignore
    @Test
    public void testEditMeta() {

    }

    @Ignore
    @Test
    public void testEditPhotos() {

    }

    @Ignore
    @Test
    public void testGetContext() throws FlickrException, IOException, SAXException {
        RequestContext requestContext = RequestContext.getRequestContext();
        PhotosetsInterface iface = flickr.getPhotosetsInterface();
        PhotoContext photoContext = iface.getContext(testProperties.getPhotoId(), testProperties.getPhotoSetId());
        Photo previousPhoto = photoContext.getPreviousPhoto();
        Photo nextPhoto = photoContext.getNextPhoto();
        assertNotNull(previousPhoto);
        assertNotNull(nextPhoto);
    }

    @Ignore
    @Test
    public void testGetInfo() throws FlickrException, IOException, SAXException {
        PhotosetsInterface iface = flickr.getPhotosetsInterface();
        Photoset photoset = iface.getInfo(testProperties.getPhotoSetId());
        assertNotNull(photoset);
        assertEquals(3, photoset.getPhotoCount());
    }

    @Ignore
    @Test
    public void testGetList() throws FlickrException, IOException, SAXException {
        PhotosetsInterface iface = flickr.getPhotosetsInterface();
        Photosets photosets = iface.getList(testProperties.getNsid());
        assertNotNull(photosets);
        assertEquals(1, photosets.getPhotosets().size());
    }

    @Ignore
    @Test
    public void testGetList2() throws FlickrException, IOException, SAXException {
        PhotosetsInterface iface = flickr.getPhotosetsInterface();
        Photosets photosets = iface.getList("26095690@N00");
        assertNotNull(photosets);
    }

    @Ignore
    @Test
    public void testGetPhotos() throws FlickrException, IOException, SAXException {
        PhotosetsInterface iface = flickr.getPhotosetsInterface();
        PhotoList photos = iface.getPhotos(
                testProperties.getPhotoSetId(),
                10,
                1
                );
        assertNotNull(photos);
        assertEquals(3, photos.size());
        assertEquals("javatest", ((Photo) photos.get(0)).getOwner().getUsername());
    }

    @Ignore
    @Test
    public void testOrderSets() throws FlickrException, IOException, SAXException {
        RequestContext requestContext = RequestContext.getRequestContext();
        PhotosetsInterface iface = flickr.getPhotosetsInterface();
        String[] photosetIds = {testProperties.getPhotoSetId()};
        iface.orderSets(photosetIds);
    }

}
