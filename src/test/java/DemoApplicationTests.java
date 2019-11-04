

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luozhe.lzspring.entity.User;
import org.luozhe.lzspring.mapper.UserMapper;
import org.luozhe.lzspring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LzApplication.class)
public class DemoApplicationTests {

	@Autowired
	private IUserService userService;

	@Resource
	UserMapper userMapper;
	@Test
	public void insertUser() {
		for (int i = 0; i < 5; i++) {
			User u=new User().setName("@xiaoming}"+i);
			u.setUuid(UUID.nameUUIDFromBytes(u.getName().getBytes())
			.toString());
			userService.save(u);
		}
//		System.out.println(new Date());
	}
	@Test
	public void insertThread() {
		List<User> users= Lists.newArrayList();
		for (int i = 0; i < 1000000; i++) {
			User u=new User().setName("@xiaoming}"+i);
			u.setUuid(UUID.nameUUIDFromBytes(u.getName().getBytes())
					.toString());
			users.add(u);
		}
		Long starttime=System.currentTimeMillis();
		System.out.println("开始插入："+starttime);
		try {
			userService.saveThread(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("完成时间："+(System.currentTimeMillis()-starttime));
	}

	@Test
	public void insertBat() {
		List<User> users= Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			User u=new User().setName("@xiaoming}"+i);
			u.setUuid(UUID.nameUUIDFromBytes(u.getName().getBytes())
					.toString());
			users.add(u);
		}
		Long starttime=System.currentTimeMillis();
		System.out.println("开始插入："+starttime);
		userMapper.insertBatchI(users);
		System.out.println("完成时间："+(System.currentTimeMillis()-starttime));
	}

	@Test
	public void selectuser() {
		List<User> users= userMapper.selectList(null);
		System.out.println(users);
	}

}
