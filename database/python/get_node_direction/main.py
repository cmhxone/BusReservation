from selenium import webdriver
from selenium.webdriver.chrome.options import Options

# Author: CMH
# Main 함수
def main():
    # 크롬 Headless로 설정
    chrome_options = Options()
    chrome_options.add_argument('--headless')

    # 크롬 드라이버로 버스 노선도를 읽어온다
    driver = webdriver.Chrome('chromedriver', options=chrome_options)
    driver.set_page_load_timeout(2000)
    driver.set_script_timeout(2000)
    driver.get('https://bis.jinju.go.kr/station/busStation.do')

    # 노선 정보를 크롤링
    # 2021-05-22 변경내용: 띄어쓰기 문제 발생으로 아래 함수 사용 안함
    # nodes = ' '.join(driver.find_element_by_id('stop_list').text.split())
    nodes = driver.find_element_by_id('stop_list').text
    assert nodes is not None, 'No Result'

    # CSV 형태로 변환
    nodes = nodes.replace('[', '')\
          .replace(']', '')\
          .replace('  ', ',')\
          .replace('방면', '')\
          .replace(' ', '')
    driver.close()

    # 로그
    # print(nodes)

    # 결과값을 파일로 저장한다
    result_file = open('node.csv', 'w')
    result_file.write(nodes)
    result_file.close()

if __name__ == '__main__' : main()

