
package com.main.server.implementation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import com.main.server.service.CommentDT;
import com.main.server.service.ObjectFactory;
import com.main.server.service.RatingEnum;
import com.main.server.service.VideoDT;
import com.main.server.service.Videos;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "VideoWebService", targetNamespace = "http://service.server.main.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface VideoWebService {


    /**
     * 
     * @param arg0
     * @return
     *     returns com.main.server.service.Videos
     * @throws EntityNotFoundException
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.server.main.com/VideoWebService/listVideosByUserRequest", output = "http://service.server.main.com/VideoWebService/listVideosByUserResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/listVideosByUser/Fault/EntityNotFoundException")
    })
    public Videos listVideosByUser(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws EntityNotFoundException
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg6
     * @return
     *     returns com.main.server.service.VideoDT
     * @throws EntityNotFoundException
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.server.main.com/VideoWebService/createVideoWithCategoryRequest", output = "http://service.server.main.com/VideoWebService/createVideoWithCategoryResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/createVideoWithCategory/Fault/EntityNotFoundException")
    })
    public VideoDT createVideoWithCategory(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6)
        throws EntityNotFoundException
    ;

    /**
     * 
     * @return
     *     returns com.main.server.service.Videos
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.server.main.com/VideoWebService/listVideosRequest", output = "http://service.server.main.com/VideoWebService/listVideosResponse")
    public Videos listVideos();

    /**
     * 
     * @param arg0
     * @return
     *     returns com.main.server.service.VideoDT
     * @throws EntityNotFoundException
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.server.main.com/VideoWebService/getVideoRequest", output = "http://service.server.main.com/VideoWebService/getVideoResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/getVideo/Fault/EntityNotFoundException")
    })
    public VideoDT getVideo(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0)
        throws EntityNotFoundException
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns com.main.server.service.Videos
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.server.main.com/VideoWebService/listVideosNotFromUserRequest", output = "http://service.server.main.com/VideoWebService/listVideosNotFromUserResponse")
    public Videos listVideosNotFromUser(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.main.server.service.Videos
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.server.main.com/VideoWebService/listVideosByCategoryRequest", output = "http://service.server.main.com/VideoWebService/listVideosByCategoryResponse")
    public Videos listVideosByCategory(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns com.main.server.service.VideoDT
     * @throws EntityNotFoundException
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.server.main.com/VideoWebService/createVideoRequest", output = "http://service.server.main.com/VideoWebService/createVideoResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/createVideo/Fault/EntityNotFoundException")
    })
    public VideoDT createVideo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5)
        throws EntityNotFoundException
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns com.main.server.service.CommentDT
     * @throws EntityNotFoundException
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.server.main.com/VideoWebService/listCommentsRequest", output = "http://service.server.main.com/VideoWebService/listCommentsResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/listComments/Fault/EntityNotFoundException")
    })
    public CommentDT listComments(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0)
        throws EntityNotFoundException
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @throws EntityNotFoundException
     */
    @WebMethod
    @Action(input = "http://service.server.main.com/VideoWebService/commentCommentRequest", output = "http://service.server.main.com/VideoWebService/commentCommentResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/commentComment/Fault/EntityNotFoundException")
    })
    public void commentComment(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        long arg3,
        @WebParam(name = "arg4", partName = "arg4")
        long arg4)
        throws EntityNotFoundException
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @throws EntityNotFoundException
     */
    @WebMethod
    @Action(input = "http://service.server.main.com/VideoWebService/commentVideoRequest", output = "http://service.server.main.com/VideoWebService/commentVideoResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/commentVideo/Fault/EntityNotFoundException")
    })
    public void commentVideo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        long arg3)
        throws EntityNotFoundException
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws EntityNotFoundException
     * @throws PrivacyException
     */
    @WebMethod
    @Action(input = "http://service.server.main.com/VideoWebService/modifyVideoRequest", output = "http://service.server.main.com/VideoWebService/modifyVideoResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/modifyVideo/Fault/EntityNotFoundException"),
        @FaultAction(className = PrivacyException.class, value = "http://service.server.main.com/VideoWebService/modifyVideo/Fault/PrivacyException")
    })
    public void modifyVideo(
        @WebParam(name = "arg0", partName = "arg0")
        VideoDT arg0,
        @WebParam(name = "arg1", partName = "arg1")
        long arg1)
        throws EntityNotFoundException, PrivacyException
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @throws EntityNotFoundException
     */
    @WebMethod
    @Action(input = "http://service.server.main.com/VideoWebService/rateVideoRequest", output = "http://service.server.main.com/VideoWebService/rateVideoResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/rateVideo/Fault/EntityNotFoundException")
    })
    public void rateVideo(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        RatingEnum arg2)
        throws EntityNotFoundException
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws EntityNotFoundException
     */
    @WebMethod
    @Action(input = "http://service.server.main.com/VideoWebService/deleteRaitingRequest", output = "http://service.server.main.com/VideoWebService/deleteRaitingResponse", fault = {
        @FaultAction(className = EntityNotFoundException.class, value = "http://service.server.main.com/VideoWebService/deleteRaiting/Fault/EntityNotFoundException")
    })
    public void deleteRaiting(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws EntityNotFoundException
    ;

}
