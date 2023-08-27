package info.vernonengle.voicegpthello.secrets

import com.google.auth.oauth2.GoogleCredentials
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths

@Component
@Profile("local")
class LocalGCPCredentialsProvider(
    @Value("classpath:ai-chat-380801-3017f46ffb30.json")
    val resource: Resource
) : GCPCredentialsProvider {
    override fun getCredentials(): GoogleCredentials {

        return GoogleCredentials.fromStream(resource.inputStream)
    }
}