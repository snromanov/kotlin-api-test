FROM bellsoft/liberica-openjdk-alpine:14

RUN apk --update add bash wget unzip sshpass openssh-client git

# Download and install gosu
ENV GOSU_VERSION 1.11
RUN set -eux; \
	\
	apk add --no-cache --virtual .gosu-deps \
		ca-certificates \
		dpkg \
		gnupg \
	; \
	\
	dpkgArch="$(dpkg --print-architecture | awk -F- '{ print $NF }')"; \
	wget -O /usr/local/bin/gosu "https://github.com/tianon/gosu/releases/download/$GOSU_VERSION/gosu-$dpkgArch"; \
	wget -O /usr/local/bin/gosu.asc "https://github.com/tianon/gosu/releases/download/$GOSU_VERSION/gosu-$dpkgArch.asc"; \
	\
    # verify the signature
	export GNUPGHOME="$(mktemp -d)"; \
	gpg --batch --keyserver hkps://keys.openpgp.org --recv-keys ; \
	gpg --batch --verify /usr/local/bin/gosu.asc /usr/local/bin/gosu; \
	command -v gpgconf && gpgconf --kill all || :; \
	rm -rf "$GNUPGHOME" /usr/local/bin/gosu.asc; \
	\
    # clean up fetch dependencies
	apk del --no-network .gosu-deps; \
	\
	chmod +x /usr/local/bin/gosu; \
    # verify that the binary works
	gosu --version; \
	gosu nobody true

ENV GRADLE_HOME /opt/gradle
ENV GRADLE_VERSION 6.3
RUN set -o errexit -o nounset \
    && wget --no-verbose --output-document=gradle.zip "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
    && unzip gradle.zip \
    && rm gradle.zip \
    && mv "gradle-${GRADLE_VERSION}" "${GRADLE_HOME}/" \
    && ln -s "${GRADLE_HOME}/bin/gradle" /usr/bin/gradle \
    && gradle --version

WORKDIR /app
RUN adduser -D -u 1000 testuser

COPY .gitlab/docker /app
RUN chown -R testuser /app
RUN gosu testuser gradle --version

COPY .gitlab/docker/entrypoint.sh /usr/local/bin/

ENTRYPOINT ["sh", "/usr/local/bin/entrypoint.sh"]
USER root
