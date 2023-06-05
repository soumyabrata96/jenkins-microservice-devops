//Declarative
pipeline {
    agent any
    // agent { docker { image 'eclipse-temurin:17-jdk-alpine' } }
    environment {
        dockerHome=tool 'myDocker'
        mavenHome=tool 'myMaven'
        PATH="$dockerHome/bin:$mavenHome/bin:$PATH"
    }
    stages
    {
        stage('Checkout') {
            steps{
                sh 'mvn --version'
                sh 'docker --version'
                echo "Build"
                echo "$PATH"
                echo "BUILD_NUMBER - $env.BUILD_NUMBER"
                echo "BUILD_ID - $env.BUILD_ID"
                echo "JOB_NAME - $env.JOB_NAME"
                echo "BUILD_TAG - $env.BUILD_TAG"
                echo "BUILD_URL - $env.BUILD_URL"
            }
        }
        stage('Compile') {
            steps{
                sh "mvn clean compile"
            }
        }
        stage('Test') {
            steps{
                sh "mvn test"
            }
        }
        stage('Integration Test') {
            steps{
                sh "mvn failsafe:integration-test failsafe:verify"
            }
        }
        stage('Package'){
            steps{
                sh "mvn package -DskipTests"
            }
        }
        stage('Build Docker Image'){
            steps{
                script{
                    dockerImage=docker.build("soumyabratamukh1/coupon-service:${env.BUILD_TAG}")
                }
            }
        }
        stage('Push Docker Image'){
            steps{
                script{
                    docker.withRegistry('https://hub.docker.com/','dockerhub'){
                        dockerImage.push();
                    }
                }
            }
        }

    }
    post{
        always{
            echo "I will show everyone my capabilities !!"
        }
        success{
            echo "Fuck Capgemini and BDN !! I will write my own success story !!"
        }
        failure{
            echo "I am total failure !!"
        }
    }
}