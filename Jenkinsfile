pipeline {
    agent: any

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout GIT') {
            steps {
                checkout scm
            }
        }
    }

}