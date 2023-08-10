package info.vernonengle.voicegpthello.secrets

import com.google.auth.oauth2.GoogleCredentials
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths

@Component
@Profile("local")
class LocalGCPCredentialsProvider : GCPCredentialsProvider {
    override fun getCredentials(): GoogleCredentials {
        val credentialsPath =
            "/Users/vernon.g.engle/projects/voice-gpt/src/main/resources/ai-chat-380801-3017f46ffb30.json" // Replace with your path
        return GoogleCredentials.fromStream(Files.newInputStream(Paths.get(credentialsPath)))
    }
}