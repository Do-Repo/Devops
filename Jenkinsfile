pipeline {
    agent any

    triggers {
        githubPush()
    }

    stages {

        stage('Checkout GIT') {
            steps {
                checkout scm
            }
        }

        stage('Show Date') {
            steps {
                script {
                    echo "Current date and time: ${new Date()}"
                }
            }
        }
        
    }
}