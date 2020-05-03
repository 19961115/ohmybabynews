package com.uestc.ohmynews.web;

import com.uestc.ohmynews.dao.CommentDao;
import com.uestc.ohmynews.dao.NewsDao;
import com.uestc.ohmynews.dao.TypeDao;
import com.uestc.ohmynews.dao.UserDao;
import com.uestc.ohmynews.entity.User;
import com.uestc.ohmynews.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@RestController
public class CommentController {
    //注入持久层
    @Autowired(required = false)
    private CommentDao commentDao;
    @Autowired(required = false)
    private NewsDao newsDao;
    @Autowired(required = false)
    private TypeDao typeDao;
    @Autowired(required = false)
    private UserDao userDao;
    @Autowired(required = false)
    private GetUser getUser;
   //实现增加评论功能
    @GetMapping("/comment/addcoment")
    public ModelAndView toAddComment(){
        return new ModelAndView("/");
    }
    @PostMapping("/comment/addcomment")
    public ModelAndView addComment(ModelAndView mv, @PathVariable int news_id,
                                   @PathVariable String content,
                                   @PathVariable int p_id,
                                   @PathVariable int reply_user_id){
        commentDao.addComment(news_id,content,p_id,reply_user_id,getUser.getUser().getUser_id());
        mv.setViewName("/");
        mv.addObject("message","评论已提交！");
        return mv;
    }

    //实现管理员删除评论
    @PostMapping("/comment/deleteCommentByComment_id")
    public ModelAndView deleteCommentByComment_id(ModelAndView mv,@PathVariable int comment_id){
        commentDao.deleteCommentByComment_id(comment_id);
        mv.setViewName("/");
        mv.addObject("message","评论已经被管理员删除！");
        return mv;
    }

    //实现当前用户删除评论
    @PostMapping("/comment/deleteCommentByComment_idAndUser_id")
    public ModelAndView deleteCommentByComment_idAndUser_id(ModelAndView mv,
                                                            @PathVariable int comment_id,
                                                            @PathVariable int user_id){
        commentDao.deleteCommentByComment_idAndUser_id(comment_id,user_id);
        mv.setViewName("/");
        mv.addObject("message","评论已删除！");
        return mv;
    }
    //通过用户id和新闻id查询评论
    @GetMapping("/comment/findCommentByNews_idAndUser_id")
    public ModelAndView findCommentByNews_idAndUser_id(ModelAndView mv,
                                                       @PathVariable int news_id,
                                                       @PathVariable int user_id){
        mv.addObject("comment",commentDao.findCommentByNews_idAndUser_id(news_id,user_id));
        mv.setViewName("/");
        return mv;
    }
    //通过评论回复查询子评论
    @GetMapping("/comment/findReplyCommentByReply_user_id")
    public ModelAndView findReplyCommentByReply_user_id(ModelAndView mv,
                                                        @PathVariable int reply_user_id){
        mv.addObject("commentList",commentDao.findReplyCommentByReply_user_id(reply_user_id));
        mv.setViewName("/");
        return mv;
}
    //通过评论查询父评论
    @GetMapping("/comment/findCommentdByP_id")
    public  ModelAndView findCommentdByP_id(ModelAndView mv,@PathVariable int p_id){
        mv.addObject("commentList",commentDao.findCommentdByP_id(p_id));
        mv.setViewName("/");
        return mv;
    }



}
