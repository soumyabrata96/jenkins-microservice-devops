//Declarative
pipeline {
    //agent any
    agent { 
        docker 
        {
            image 'eclipse-temurin:17-jdk-alpine' 
        } 
    }
    stages
    {
        stage('Build') {
            steps{
                sh 'java --version'
                echo "Build"
            }
        }
        stage('Test') {
            steps{
                echo "Test"
            }
        }
        stage('Integration Test') {
            steps{
                echo "Integration Test"
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