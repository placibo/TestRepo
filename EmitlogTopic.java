import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class EmitlogTopic
{
	private static final String EXCHANGE_NAME = "topic_logs";
	
	public static void main(String args[]) throws Exception
	{
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		
		String routingKey="A";
		String message="Hello RMQ";
		
		channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
		
		System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
		
		channel.close();
		connection.close();
	}
}