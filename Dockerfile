FROM maven:3.6.3-ibmjava-8

WORKDIR /tests

# Set the locale
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

COPY . .

CMD mvn -q test