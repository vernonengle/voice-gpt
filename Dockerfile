FROM openjdk:17-alpine AS builder

WORKDIR /opt/
COPY . /opt/voice-gpt
RUN cd /opt/voice-gpt && ./gradlew clean build

FROM openjdk:17-alpine AS runtime

COPY --from=builder /opt/voice-gpt/build/libs/*.jar /opt/

RUN rm -f /opt/*-plain.jar  \
    && echo 'java -Xmx2048m -jar /opt/voice-gpt-*.jar' > /run.sh \
    && chmod +x /run.sh

CMD ["/bin/sh", "-c", "/run.sh"]

EXPOSE 8080

