package cn.fciasth.zhihu;

import cn.fciasth.zhihu.bean.Question;
import cn.fciasth.zhihu.bean.User;
import cn.fciasth.zhihu.dao.QuestionDAO;
import cn.fciasth.zhihu.dao.QuestionRepository;
import cn.fciasth.zhihu.dao.UserDAO;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZhihuApplicationTests {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	public void contextLoads() {
		/*Random random = new Random();
		for (int i = 1;i<=10 ;i++){
			User user = new User();
			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
			user.setName(String.format("USER%d",i));
			user.setPassword("");
			user.setSalt("");
			userDAO.addUser(user);
			user.setPassword("xx");
			userDAO.updatePassword(user);
		}*/
		User user = userDAO.selectById(24);
		user.setPassword("xxxxx");
		userDAO.updatePassword(user);
		userDAO.deleteById(22);
		System.out.println(userDAO.selectById(23));
	}



	@Test
	public void testElastic(){

		List<Question> questions = questionDAO.selectLatestQuestions(0, 10, 160);
		for (Question question:questions
			 ) {
			questionRepository.index(question);
		}
	}

	@Test
	public void testElastic01(){
		for (Question question:questionRepository.findQuestionsByContentLikeOrTitleLike("手机","手机")
				) {
			System.out.println(question);
		}
	}


	@Test
	public void test01(){
//		for (int i = 1;i<=10 ;i++){
//			Question question = new Question();
//			question.setTitle(String.format("TITLE%d",i));
//			Date date = new Date();
//			date.setTime(date.getTime()+1000*3600*i);
//			question.setCreatedDate(date);
//			question.setCommentCount(i);
//			question.setUserId(i+1);
//			question.setContent(String.format("BLABALBALABLABALAB %d",i));
//			questionDAO.addQuestion(question);
//		}
		System.out.println(questionDAO.selectLatestQuestions(0,0,5));
	}
}
