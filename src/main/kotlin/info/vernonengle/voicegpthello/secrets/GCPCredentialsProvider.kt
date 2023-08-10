package info.vernonengle.voicegpthello.secrets

import com.google.auth.oauth2.GoogleCredentials

interface GCPCredentialsProvider {

    fun getCredentials(): GoogleCredentials
}
