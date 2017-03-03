import argparse
import requests
from collections import OrderedDict


def parse():
    '''
    Handles argument parsing
    :return: arguments in dictionary format
    '''
    parser = argparse.ArgumentParser(description='Print the sum of estimates for given issues.')
    parser.add_argument('types', metavar='type', type=str, nargs='+', help='type of issues to print')
    parser.add_argument('--url', type=str, help='base url for jira rest server')
    args = parser.parse_args()
    return vars(args)


def estimate(jira_types, url):
    '''
    Given the types, walk thru each type and get the total estimated points.
    :param jira_types: list of types
    :param url: url for request usage
    :return: a dictionary with format {'type' : estimate}
    '''
    d = OrderedDict()
    for jira_type in jira_types:
        d[jira_type] = 0

    # Set is used to prevent duplicates in types
    for jira_type in set(jira_types):
        r = requests.get(url+'/issuetypes/'+jira_type)
        if r.status_code != 200:
            print 'Warning: {} not found.'.format(jira_type)
            continue
        json = r.json()
        for issue in json['issues']:
            issue_type = requests.get(url+issue)
            if issue_type.status_code == 200:
                d[jira_type] += issue_type.json()['estimate']
            else:
                print '{} not found.'.format(issue_type)
    return d


def print_estimates(estimate_dict):
    '''
    Print the dictionary in 'type' : points format
    :param estimate_dict:
    :return:
    '''
    for jira_type, points in estimate_dict.iteritems():
        print '{} : {}'.format(jira_type, points)


if __name__ == "__main__":
    args = parse()
    if args['url'] == None:
        url = 'http://localhost:8080'
    else:
        url = args['url']
    d = estimate(args['types'], url)
    print_estimates(d)
