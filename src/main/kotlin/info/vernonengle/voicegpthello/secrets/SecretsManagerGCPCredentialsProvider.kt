package info.vernonengle.voicegpthello.secrets

import com.google.auth.oauth2.GoogleCredentials
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse

@Component
@Profile("dev")
class SecretsManagerGCPCredentialsProvider : GCPCredentialsProvider {

    override fun getCredentials(): GoogleCredentials {
        val secretName = "voice-gpt/google-api-key"
        val region: Region = Region.of("ap-southeast-1")

        // Create a Secrets Manager client
        val client: SecretsManagerClient = SecretsManagerClient.builder()
            .region(region)
            .build()
        val getSecretValueRequest: GetSecretValueRequest = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build()
        val getSecretValueResponse: GetSecretValueResponse
        getSecretValueResponse = try {
            client.getSecretValue(getSecretValueRequest)
        } catch (e: Exception) {
            // For a list of exceptions thrown, see
            // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
            throw e
        }
        val secret: String = getSecretValueResponse.secretString()
        //        val credentialsPath = "/Users/vernon.g.engle/projects/voice-gpt/src/main/resources/ai-chat-380801-3017f46ffb30.json" // Replace with your path
//        val credentials = GoogleCredentials.fromStream(Files.newInputStream(Paths.get(credentialsPath)))
        return GoogleCredentials.fromStream(secret.byteInputStream())
    }
}