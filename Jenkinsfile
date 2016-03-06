node {
  //
  stage 'Checkout'
  //

  checkout scm

  //
  stage 'Build'
  //

  sh "./gradlew clean build"

  // archive JUnit results
  step([$class: 'JUnitResultArchiver', testResults: '**/TEST-*.xml'])

  // Archive bundles
  archive '**/libs/*.jar'
}
